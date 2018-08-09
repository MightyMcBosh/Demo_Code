using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Boggle
{
    public class GameData
    {
        public string id { get; set; }
        public Player Player1 {  get; set; }
        public Player Player2 { get; set; }
        public string GameState { get; set; }
        public long TimeLimit { get; set; }
        public long StartTime { get; set; } 
        public BoggleBoard board; 
    }

    public class NewUser
    {
        public string Nickname { get; set; }
    }
    
    public class NewGame
    {
        public string UserToken { get; set; }
        public int TimeLimit { get; set; }
    }

    public class User
    {
        public string UserToken { get; set; }
    }

    public class NewWord
    {
        public string UserToken { get; set; }
        public string Word { get; set; }
    }

    public class ReturnGame
    {
        public string GameID { get; set; }
    }

    public class _Score
    {
        public int Score { get; set; }
    }
    public class Player_lite
    {
        public string Nickname { get; set; }
        public int Score { get; set; }

    }
    public abstract class ReturnObject
    { };
    
    public class Ret1 :ReturnObject
    {
        public string GameState { get; set; }
    }
    public class Ret2 :ReturnObject
    {
        public string GameState { get; set; }
        public int TimeLeft { get; set; }

        public User Player1 { get; set; }
        public User Player2 { get; set; }
        public struct User
        {
            public int Score { get; set; } 
        }

    }
    public class Ret3:ReturnObject
    {
       public string GameState { get; set; }
        public string Board { get; set; }
        public int TimeLimit { get; set; }
       public  int TimeLeft { get; set; }

      public  User Player1 { get; set; }
        public User Player2 { get; set; }
       public  struct User
        {
            public string Nickname { get; set; }
            public int Score { get; set; }
        }

    }
    public class Ret4:ReturnObject
    {
        public string GameState { get; set; }
        public string Board { get; set; }
        public int TimeLimit { get; set; }
        public int TimeLeft { get; set; }

        public User Player1 { get; set; }
        public User Player2 { get; set; }
        public struct User
        {
            public string Nickname { get; set; }
            public int Score { get; set; }
            public BoggleWord[] WordsPlayed { get; set; } 
        }

    }







    public class Player
    {
        public string Nickname { get; set; }
        public List<BoggleWord> WordsPlayed; 
        public int Score;
        public string token {get; set; }
        public Player(string nickname)
        {
            this.Nickname = nickname;
            WordsPlayed = new List<BoggleWord>();
            Score = 0; 
        }

        public void PlayWord(string Word, int score)
        {
            BoggleWord word = new BoggleWord()
            {
                Word = Word,
                Score = score
            };

            WordsPlayed.Add(word);
            this.Score += score; 
        }

        public  bool Equals(Player p)
        {
            return (token == p.token);
        }

    }
    public struct BoggleWord
    {
        public string Word { get; set; }
        public int Score { get; set; } 
    }

    
}