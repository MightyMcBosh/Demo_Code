using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading;
using System.Threading.Tasks;
using CustomNetworking;

namespace Boggle
{
    class Program
    {

        static void Main()
        {



            new BoggleServer(); 
            // This is our way of preventing the main thread from
            // exiting while the server is in use
            Console.ReadLine();
        }
        class BoggleServer
        {
            //Set up very similarly to the chat server. 

            //regexs that correspond to various commands
            
            HttpStatusCode status;

             SSListener server = new SSListener(60000, Encoding.UTF8);

            static BoggleService service = new BoggleService();

            public BoggleServer()
            {
                server.Start();
                server.BeginAcceptSS(ConnectionRequested, null);
            }
            
            private void ConnectionRequested(SS ss, object payload)
            {
                
                new BoggleGameConnection(ss,service);
                server.BeginAcceptSS(ConnectionRequested, null);
            }

            class BoggleGameConnection
            {
                //Necessary regular expressions to pick apart http requests
                Regex isPut = new Regex(@"PUT \/BoggleService.svc\/.{1,}");
                Regex isPost = new Regex(@"POST \/BoggleService.svc\/.{1,}");
                Regex isGet = new Regex(@"GET \/BoggleService.svc\/.{1,}",RegexOptions.IgnoreCase);
                //
                Regex getNumBytes = new Regex(@"Content-Length: (\d{1,})");
                Regex getJsonObject = new Regex("(\\{ *\"\\w{1,}\"\\:\"\\w{1,}\" *\\})");
                BoggleService service; 
                SS ss;
                public BoggleGameConnection(SS ss, BoggleService s)
                {
                    this.ss = ss;
                    service = s; 
                    ss.BeginReceive(MsgRecieved, null); 
                }
              
                //Fired after a message is recieved on a string socket
                private void MsgRecieved(string s, object payload)
                {
                    Console.WriteLine(s);  
                    if(IsPost(s))
                    {
                        HandlePost(s);
                        
                    }
                    else if(IsGet(s))
                    {
                        HandleGet(s); 
                    }
                    else if(IsPut(s))
                    {
                        HandlePut(s); 
                    }

                    
                    
                }
                //private helper methods to handle HTTP requests
                private void HandlePut(string s)
                {
                    var cmds = Regex.Match(s, @"PUT \/BoggleService.svc\/games\/(\S{1,15})");
                    string gameID = ""; 
                    if (cmds.Groups.Count > 0 && cmds.Groups[1].ToString().Trim().ToLower().Length > 0)
                    {
                        gameID = cmds.Groups[1].ToString().Trim().ToLower();
                    }

                    
                }

                private void HandleGet(string s)
                {
                   
                        Console.WriteLine("Get");
                        var cmds = Regex.Match(s, @"GET \/BoggleService.svc\/([^\r\n\t\f\v ]{1,15})\/([^\r\n\t\f\v ]{1,})\/");
                        if (cmds.Groups.Count > 0 && cmds.Groups[1].Value.Trim().ToLower() == "games")
                        {
                            string res = "";
                            string data = "";
                            string oldData = "start";
                            //get all the data
                            while (data != null)
                            {


                                ss.BeginReceive((a, b) => { data = a; }, null);
                                if (data != null)
                                    res += data;
                                if (data == oldData)
                                    break;

                                oldData = data;
                                Thread.Sleep(1);
                            }
                            int numBytes = -1;
                            //Clean new lines out of the string cause they're a pain in the butt to deal with
                            res = Clean(res);
                            //extract data from the http request
                            //This is for content length 

                            var match = getNumBytes.Match(res);
                            if (match.Success && int.TryParse(match.Groups[1].Value, out numBytes))
                            {
                                match = getJsonObject.Match(res);
                                if (match.Success && numBytes >= 0)
                                {
                                    HttpStatusCode code;
                                    string json = match.Groups[1].Value;
                                     dynamic stuff = Newtonsoft.Json.JsonConvert.DeserializeObject(json);
                                //send to the boggle service
                                //ReturnToClient(HttpStatusCode, new Ret4());
                                //ToDo
                                }


                            }
                        }

                    
                }

                private void HandlePost(string s)
                {
                    Console.WriteLine("post");    
                    var cmds = Regex.Match( s, @"POST \/BoggleService.svc\/([^\r\n\t\f\v ]{1,15})");
                    if (cmds.Groups.Count > 0 && cmds.Groups[1].Value.Trim().ToLower() == "users")
                    {
                        string res = "";
                        string data = "";
                        string oldData = "start";
                        //get all the data
                        while (data != null)
                        {
                          
                          
                            ss.BeginReceive((a, b) => { data = a; }, null);
                            if (data != null)
                                res += data;
                            if (data == oldData)
                                break;

                            oldData = data;
                            Thread.Sleep(1); 
                        }
                        int numBytes = -1;
                        //Clean new lines out of the string cause they're a pain in the butt to deal with
                        res = Clean(res);
                        //extract data from the http request
                        //This is for content length 

                        var match = getNumBytes.Match(res);
                        if (match.Success && int.TryParse(match.Groups[1].Value, out numBytes))
                        {
                            match = getJsonObject.Match(res);
                            if (match.Success && numBytes >= 0)
                            {
                                HttpStatusCode code; 
                                string json = match.Groups[1].Value;
                                NewUser nickname = Newtonsoft.Json.JsonConvert.DeserializeObject<NewUser>(json);
                                //send to the boggle service
                                User user =  service.CreateUser(nickname, out code);
                                ReturnToClient(code, user); 
                            
                            }


                        }
                    }
                    else if (cmds.Groups.Count > 0 && cmds.Groups[1].ToString().Trim().ToLower().Contains("games")) 
                    {
                     //todo   
                    }
                }
                //the method that buts 
                private void ReturnToClient(HttpStatusCode code, object ReturnObject)
                {
                    
                    string sb = "HTTP/1.1 " + (Int32)code + " " + code.ToString();
                    string send = "";
                    if ((Int32)code != 500)
                    {
                        string ret = Newtonsoft.Json.JsonConvert.SerializeObject(ReturnObject);

                        send = sb + "\r" + "Content-Length: " + Encoding.UTF8.GetByteCount(ret.ToCharArray())
                            + "Content-Type: application.json; charset=utf8" + "\r"
                            + "\r"
                            + ret;
                    }
                    else
                       send = sb; 

                    ss.BeginSend(send, (a, b) => { ss.Close(); Console.Write("Sent");  }, null);
                }

                private bool IsPut(string s)
                {
                    if (s != null)
                        return Regex.IsMatch(s, @"PUT \/BoggleService.svc\/.{1,}");
                    else
                        return false; 

                }

                private bool IsGet(string s)
                {
                    if (s != null)
                        return Regex.IsMatch(s, @"GET \/BoggleService.svc\/.{1,}");
                    else
                        return false; 
                }

                private bool IsPost(string s)
                {
                    if (s != null)
                        return Regex.IsMatch(s, @"POST \/BoggleService.svc\/\S{1,}");
                    else
                        return false; 
                }
                //helper method I borrowed to help clean the line endings out of the strings and replace them with spaces. 
                private string Clean(string value)
                {
                    if (String.IsNullOrEmpty(value))
                    {
                        return value;
                    }
                    string lineSeparator = ((char)0x2028).ToString();
                    string paragraphSeparator = ((char)0x2029).ToString();

                    return value.Replace("\r\n", " ")
                                .Replace("\n", " ")
                                .Replace("\r", " ")
                                .Replace(lineSeparator, " ")
                                .Replace(paragraphSeparator, " ");
                }
            }
            
        }



    }
}
