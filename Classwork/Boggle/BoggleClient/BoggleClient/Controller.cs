using System;
using Newtonsoft.Json;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Net.Http;
using System.Dynamic;
using System.Windows.Forms;

namespace BoggleClient
{
    class Controller
    {
        private CancellationTokenSource tokenSource;

        private IBoggleClient game;
        private bool isActive = false; 
        private string UserHash;
        private string nickname1;
        private string nickname2;
        private int player1score;
        private int player2score;
        private List<string> words;
        private string lastUsedServer;
        private string board;
        private string gameID;
        private string gameState;
        private int timeLimit;
        private int lastTime; 
        
       
       
        public Controller(IBoggleClient game)
        {
            this.game = game;
            words = new List<string>();
            game.CreateUser += CreateUser;
            game.CancelJoinGame += CancelSearch;
            game.JoinGame += Join;
            game.PlayWord += Play;
            game.Update += Update;
            timeLimit = 60;
        }

        private async void Update(bool isBrief,string server)
        {
            try
            {
                HttpClient client = CreateNewClient(server);
                tokenSource = new CancellationTokenSource();
                string param;
                if (isBrief)
                    param = "yes";
                else
                    param = "no";

                   

                HttpResponseMessage response = await client.GetAsync("BoggleService.svc/games/"+ gameID +"?Brief="+param);

                if (response.StatusCode == System.Net.HttpStatusCode.OK && isBrief)
                {
                    String result = response.Content.ReadAsStringAsync().Result;
                    // UserHash = (string)JsonConvert.DeserializeObject(result);
                    dynamic results = JsonConvert.DeserializeObject(result);
                    gameState = (string)results.GameState;
                    game.setError("");
                    if (!gameState.Equals("pending"))
                    {
                        player1score = (int)results.Player1.Score;
                        player2score = (int)results.Player2.Score;
                        lastTime = results.TimeLeft;
                    }
                    

                }
                else if (response.StatusCode == System.Net.HttpStatusCode.OK && !isBrief)
                {
                    String result = response.Content.ReadAsStringAsync().Result;
                    // UserHash = (string)JsonConvert.DeserializeObject(result);
                    dynamic results = JsonConvert.DeserializeObject(result);
                    gameState = (string)results.GameState;
                    game.setError("");
                    if (gameState != "pending")
                    {
                        if(gameState == "completed")
                        {

                            List<string> p1words = new List<string>();
                            dynamic opwords1 = results.Player1.WordsPlayed;
                                List<string> p2words = new List<string>();
                            dynamic opwords2 = results.Player2.WordsPlayed;
                            foreach (dynamic d in opwords1)
                            {
                                p1words.Add((string)d.Word + ": " + d.Score.ToString());
                            }
                            foreach(dynamic d in opwords2)
                            {
                                p2words.Add((string)d.Word + ": " + d.Score.ToString()); 
                            }

                            game.SetOppposingWords(p2words.ToArray());
                            game.setWordsPlayed(p1words.ToArray());
                            words.Clear(); 

                        }
                        player1score = (int)results.Player1.Score;
                        player2score = (int)results.Player2.Score;
                        board = results.Board;
                        game.drawBoard(board);
                        timeLimit = results.TimeLimit;
                        lastTime = results.TimeLeft;
                        nickname1 = results.Player1.Nickname;
                        nickname2 = results.Player2.Nickname;
                        // game.setTimer(results.TimeLeft, results.TimeLimit);
                        game.setName(nickname1, nickname2);
                        game.gotData(); 
                                               
                    }


                }
                else
                {
                   game.setError("Game id not found " + response.StatusCode + "\n" + response.ReasonPhrase);
                }
            }
            catch (Exception e)
            {
                game.setError(e.ToString());
            }
            finally
            {
                
                    game.setTimer(lastTime, timeLimit);
                    game.setScore(1, player1score);
                    game.setScore(2, player2score);
                game.setConnectionStatus(gameState);
                    if (gameState != "completed")
                {
                    game.setWordsPlayed(words.ToArray());
                }
            }
        }

        private async void Play(string word,string server)
        {
           
            try
            {
                HttpClient client = CreateNewClient(server);
                tokenSource = new CancellationTokenSource();
                dynamic task = new ExpandoObject();
                task.UserToken = UserHash;
                task.Word = word; 
                StringContent content = new StringContent(JsonConvert.SerializeObject(task), Encoding.UTF8, "application/json");

                HttpResponseMessage response = await client.PutAsync("BoggleService.svc/games/" + gameID, content);

                if (response.StatusCode == System.Net.HttpStatusCode.OK)
                {
                    String result = response.Content.ReadAsStringAsync().Result;
                    // UserHash = (string)JsonConvert.DeserializeObject(result);
                    dynamic results = JsonConvert.DeserializeObject(result);
                    player1score += (int)results.Score;
                    game.setScore(1, player1score);
                    words.Add(word + ": " + results.Score.ToString()) ;
                }
                else
                {
                    MessageBox.Show("Not Active User " + response.StatusCode + "\n" + response.ReasonPhrase);
                }
            }
            catch (Exception e)
            {
                MessageBox.Show(e.ToString());
            }
        }

        private async void Join(string name, string server, int time)
        {
           

            try
            {
                HttpClient client = CreateNewClient(server);
                tokenSource = new CancellationTokenSource();
                dynamic task = new ExpandoObject();
                task.UserToken = UserHash;
                task.TimeLimit = time; 
                StringContent content = new StringContent(JsonConvert.SerializeObject(task), Encoding.UTF8, "application/json");

                HttpResponseMessage response = await client.PostAsync("BoggleService.svc/games", content);

                if (response.StatusCode == System.Net.HttpStatusCode.Accepted)
                {
                   
                    String result = response.Content.ReadAsStringAsync().Result;
                    // UserHash = (string)JsonConvert.DeserializeObject(result);
                    dynamic results = JsonConvert.DeserializeObject(result);
                    gameID = ((string)results.GameID);
                    game.setConnectionStatus("pending");
                    isActive = true; 
                    

                }
               else if (response.StatusCode == System.Net.HttpStatusCode.Created)
                {

                    String result = response.Content.ReadAsStringAsync().Result;
                    // UserHash = (string)JsonConvert.DeserializeObject(result);
                    dynamic results = JsonConvert.DeserializeObject(result);
                    gameID = ((string)results.GameID);
                    game.setConnectionStatus("active");
                    isActive = true;

                }
                else if (response.StatusCode == System.Net.HttpStatusCode.Forbidden)
                {

                    MessageBox.Show("User or Time invalid");

                }
                else
                {
                    MessageBox.Show("Error checking for game: " + response.StatusCode + "\n" + response.ReasonPhrase);
                }
            }
            catch (Exception e)
            {
                MessageBox.Show(e.ToString());
            }


        }

        private async void CancelSearch(string server)
        {
            
            try
            {
                HttpClient client = CreateNewClient(server);
                tokenSource = new CancellationTokenSource();
                dynamic task = new ExpandoObject();
                task.UserToken = UserHash;
               
                StringContent content = new StringContent(JsonConvert.SerializeObject(task), Encoding.UTF8, "application/json");

                HttpResponseMessage response = await client.PutAsync("BoggleService.svc/games", content);

                if (response.StatusCode == System.Net.HttpStatusCode.OK)
                {

                    MessageBox.Show("Successfully removed from queue");
                    game.setConnectionStatus("disconnected");
                    isActive = false;

                }
                else
                {
                    MessageBox.Show("Not Active User " + response.StatusCode + "\n" + response.ReasonPhrase);
                }
            }
            catch (Exception e)
            {
                MessageBox.Show(e.ToString());
            }

        }

        private async void CreateUser(string name, string server, int time)
        {
            nickname1 = name; 
            
            try
            {
                HttpClient client = CreateNewClient(server);
                tokenSource = new CancellationTokenSource();
                dynamic task = new ExpandoObject();
                task.Nickname = name;
                StringContent content = new StringContent(JsonConvert.SerializeObject(task), Encoding.UTF8, "application/json");

                HttpResponseMessage response = await client.PostAsync("BoggleService.svc/users", content);

                if (response.IsSuccessStatusCode)
                {
                    String result = response.Content.ReadAsStringAsync().Result;
                    // UserHash = (string)JsonConvert.DeserializeObject(result);
                    dynamic results = JsonConvert.DeserializeObject(result);
                    UserHash = (string)results.UserToken;
                    //Join(name, server, time); 
                    game.EnablePlay(); 
                }
                else
                {
                    MessageBox.Show("Error registering: " + response.StatusCode + "\n" + response.ReasonPhrase);
                }
            }
            catch (Exception e)
            {
                MessageBox.Show(e.ToString());
            }
        }
        /// <summary
        ///create a client object to send and recieve Rest calls from 
        /// </summary>
        /// <param name="server">string containing the server</param>
        /// <returns></returns>
        private static HttpClient CreateNewClient(string server)
        {
           //borrowed from the 
            HttpClient client = new HttpClient();
            client.BaseAddress = new Uri(server);
            client.DefaultRequestHeaders.Accept.Clear();
            client.DefaultRequestHeaders.Add("Accept", "application/json");
            return client; 
        }

        

    }
}
