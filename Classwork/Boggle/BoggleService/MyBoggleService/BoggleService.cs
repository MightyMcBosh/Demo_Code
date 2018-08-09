using System;
using System.Collections.Generic;
using System.Dynamic;
using System.IO;
using System.Net;

using System.Threading;
using static System.Net.HttpStatusCode;
 

namespace Boggle
{
    public class BoggleService
    { 

        //Static variables
        private static int GameCount = 0;
        private static readonly Dictionary<string, Player> players = new Dictionary<string, Player>();
        private static readonly Dictionary<string, GameData> games = new Dictionary<string, GameData>();
        private static GameData pending = new GameData()
        {
            id = "G" + GameCount.ToString(),
            Player1 = new Player(""),
            Player2 = new Player(""),
            GameState = "pending",
            TimeLimit = 0,
            board = new BoggleBoard()
        };
        private static int pendingTimeLimit = 0; 
        private static readonly object sync = new object();
        
        private static HashSet<string> dict = new HashSet<string>();
        /// <summary>
        /// The most recent call to SetStatus determines the response code used when
        /// an http response is sent.
        /// </summary>
        /// <param name="status"></param>
        private static void SetStatus(HttpStatusCode status)
        {
                               
        }

        /// <summary>
        /// Returns a Stream version of index.html.
        /// </summary>
        /// <returns></returns>
        public Stream API()
        {
            SetStatus(OK);
            return File.OpenRead(AppDomain.CurrentDomain.BaseDirectory + "index.html");
        }

        public void CancelJoinRequest(User UserToken, out HttpStatusCode code)
        {
            lock (sync)
            {
                if (UserToken.UserToken == null || !players.ContainsKey(UserToken.UserToken) || pending == null)
                {
                    code = Forbidden; 
                    return;
                }
                else
                {
                    Player player = pending.Player1;
                    Player player2;
                    players.TryGetValue(UserToken.UserToken, out player2);

                    if (player.token != player2.token)
                    {
                        code = Forbidden; 
                        return;
                    }
                    else
                    {
                        pending.Player1 = new Player("");
                        code = OK; 
                    }
                }
            }
        }

        public User CreateUser(NewUser user, out HttpStatusCode code)
        {
            lock (sync)
            {
                if (dict.Count == 0)
                {
                    string[] dicts = File.ReadAllLines(AppDomain.CurrentDomain.BaseDirectory + "../../dictionary.txt");
                    foreach(string s in dicts)
                    {
                        dict.Add(s);
                    }
                }
                
                if (user.Nickname == null || user.Nickname.Trim().Length == 0 || user.Nickname.Trim().Length > 50)
                {
                    code = Forbidden; 
                    return null;
                }
                Player player = new Player(user.Nickname.Trim());
                string UserToken = Guid.NewGuid().ToString();
                player.token = UserToken; 
                players.Add(UserToken, player);
                //SetStatus(HttpStatusCode.Created);
                User x = new User();
                x.UserToken = UserToken;

                code = Created; 
                return x;
            }
           
        }

        public Ret4 GameStatus(string brief, string GameID, out HttpStatusCode code)
        {
           
            lock (sync)
            {

                if (!games.ContainsKey(GameID))
                {
                    if (pending.id != GameID)
                    {
                        code = Forbidden; 
                        return null;
                    }
                    else
                    {
                        code = OK; 

                        return new Ret4() { GameState = "pending" };
                        // return null; 
                    }
                }
                else
                {
                    GameData game;
                    games.TryGetValue(GameID, out game);
                    if (brief == "yes" && game.GameState != "completed")
                    {
                        code = OK; 
                        Ret4 result = new Ret4()
                        {
                            GameState = game.GameState,

                            TimeLeft = (int)(game.TimeLimit - (DateTime.Now.Ticks / TimeSpan.TicksPerSecond - game.StartTime)),
                            Player1 = new Ret4.User()
                            {
                                Score = game.Player1.Score,
                            },
                            Player2 = new Ret4.User()
                            {
                                Score = game.Player1.Score,
                            },
                        };
                        if(result.TimeLeft <= 0)
                        {
                            result.TimeLeft = 0;
                            game.GameState = "completed";
                        }
                        
                        //return null;
                        return result;
                    }
                    else if(brief == "yes" && game.GameState == "completed")
                    {
                        code = OK; 
                        Ret4 result = new Ret4()
                        {
                            GameState = game.GameState,

                            TimeLeft = 0,
                            Player1 = new Ret4.User()
                            {
                                Score = game.Player1.Score,
                            },
                            Player2 = new Ret4.User()
                            {
                                Score = game.Player1.Score,
                            },
                        };
                        return result; 
                    }
                
                    else
                    {
                        if(game.GameState == "completed")
                        {
                            Ret4 result = new Ret4();
                            code = OK;  
                            result.GameState = game.GameState;
                            result.Board = game.board.ToString().ToUpper();
                            result.TimeLimit = (int)game.TimeLimit;
                            result.TimeLeft = 0;
                            result.Player1 = new Ret4.User()
                            {
                                Nickname = game.Player1.Nickname,
                                Score = game.Player1.Score,
                                WordsPlayed = game.Player1.WordsPlayed.ToArray()
                            };
                            result.Player2 = new Ret4.User()
                            {
                                Nickname = game.Player2.Nickname,
                                Score = game.Player2.Score,
                                WordsPlayed = game.Player2.WordsPlayed.ToArray()
                            };

                            return result;
                        }
                        else
                        {
                            Ret4 result = new Ret4();
                            code = OK; 
                            result.GameState = game.GameState;
                            result.Board = game.board.ToString().ToUpper();
                            result.TimeLimit = (int)game.TimeLimit;
                            result.TimeLeft = result.TimeLimit - (int)(DateTime.Now.Ticks / TimeSpan.TicksPerSecond - game.StartTime);
                            if(result.TimeLeft <= 0 )
                            {
                                game.GameState = "completed";
                            }

                            result.Player1 = new Ret4.User()
                            {
                                Nickname = game.Player1.Nickname,
                                Score = game.Player1.Score,

                            };
                            result.Player2 = new Ret4.User()
                            {
                                Nickname = game.Player2.Nickname,
                                Score = game.Player2.Score,
                               
                            };

                            return result;
                        }
                    }
                }
            }
        }

        public ReturnGame JoinGame(NewGame _game, out HttpStatusCode code)
        {
            lock (sync)
            {
                if (!players.ContainsKey(_game.UserToken) || _game.TimeLimit < 5 || _game.TimeLimit > 120)
                {
                    code = Forbidden; 
                    return null;
                }
                Player player;
                players.TryGetValue(_game.UserToken, out player);
                
                if (pending.Player1.token == player.token)
                {
                    code = Conflict; 
                    return null;
                }
                else if (pending.Player1.Nickname == "" && pending.Player2.Nickname == "")
                {
                    pending.Player1 = player;
                    pendingTimeLimit = _game.TimeLimit;
                    code = Accepted; 
                    ReturnGame rg = new ReturnGame();
                    rg.GameID = pending.id;
                    return rg;
                }
                else if (pending.Player1.Nickname != "" && pending.Player2.Nickname == "" && player != pending.Player1)
                {
                    pending.Player2 = player;
                    pending.GameState = "active";
                    pending.StartTime = DateTime.Now.Ticks / TimeSpan.TicksPerSecond;
                    pending.TimeLimit = (int)((double)(pendingTimeLimit + _game.TimeLimit) / 2.0);
                    pending.Player1.Score = 0;
                    pending.Player2.Score = 0; 
                    games.Add(pending.id, pending);
                    GameCount++;

                    ReturnGame rg = new ReturnGame();
                    rg.GameID = pending.id;
                   
                    pending = new GameData()
                    {
                        id ="G" + GameCount.ToString(),
                        Player1 = new Player(""),
                        
                        Player2 = new Player(""),
                        GameState = "pending",
                        TimeLimit = 0,
                        board = new BoggleBoard()
                    };
                    code = Created; 
                    return rg;
                }
                code = HttpStatusCode.NoContent; 
                return null; //should never make it here
            }
        }

        public _Score Score(string UserToken, string Word, string GameID, out HttpStatusCode code)
        {
            lock (sync)
            {
                GameData game = new GameData();
                Player player;

                if (Word == null || Word.Trim().Length == 0 || Word.Trim().Length > 30 || !players.TryGetValue(UserToken, out player))
                {
                    code = Forbidden; 
                    _Score ret = new _Score();
                    ret.Score = 0; 
                    return ret;
                }
                
                if (!games.TryGetValue(GameID, out game))
                {
                    code = Forbidden; 
                    _Score ret = new _Score();
                    ret.Score = 0;
                    return ret;
                }

                if (player != game.Player1 && player != game.Player2)
                {
                    code = Forbidden; 
                    _Score ret = new _Score();
                    ret.Score = 0;
                    return ret;
                }

                if (game.GameState != "active")
                {
                    code = Conflict; 
                    _Score ret = new _Score();
                    ret.Score = 0;
                    return ret;
                }

                else
                {
                    string normalizedWord = Word.Trim().ToUpper();
                    int score = 0;
                    if (game.board.CanBeFormed(normalizedWord))
                    {
                        score = GetScore(normalizedWord);
                    }
                    if (!dict.Contains(normalizedWord))
                    {
                        score = -1;
                    }
                    if (!player.WordsPlayed.Contains(new BoggleWord() { Word = normalizedWord, Score = score }))
                    {
                        player.PlayWord(normalizedWord, score);
                    }
                    //else //I don't remember why this is here, might be important
                    //{
                    //    score = 0;
                    //}   
                    code = OK; 
                    _Score ret = new _Score();
                    ret.Score = score;
                    return ret;
                }
            }
        }
  

        public int GetScore(string word)
        {
            int size = word.Length;
            if (size < 3)
                return 0;
            else if (size == 3)
                return 1;
            else if (size == 4)
                return 1;
            else if (size == 5)
                return 2;
            else if (size == 6)
                return 3;
            else if (size == 7)
                return 4;
            else if (size >= 8)
                return 11;

            //if it ever gets here im the worlds lousiest programmer
            return 42;
        }
    }
}