using System;
using System.Drawing;

namespace BoggleClient
{
    public interface IBoggleClient
    {
        /// <summary>
        /// 
        /// Draws the game board for the game. Before calling this method, ensure that the the letters are in order in a 16 character string. 
        /// </summary>
        /// <param name="g"> graphics object </param>
        /// <param name="boggleLetters"> string of letters containing the board for the game</param>
        void drawBoard(string boggleLetters);
        /// <summary>
        /// sets an error msg
        /// </summary>
        /// <param name="msg"></param>
        void setError(string msg);
        /// <summary>
        /// 
        /// Sets the score for either player 1 or 2
        /// </summary>
        /// <param name="player">player to change score for </param>
        /// <param name="score">new score</param>
        void setScore(int player, int score);
        /// <summary>
        ///Sets the connection status of the game.
        /// </summary>
        /// <param name="code">string, will either be pending, completed, active, or inactive. if no conection, set to disconnected.</param>
        void setConnectionStatus(string code);
        /// <summary>
        /// Words that you have already played in the game.
        /// </summary>
        /// <param name="words">list of played words</param>
        void setWordsPlayed(string[] words);
        /// <summary>
        /// Xml comment cause hes looking over my shoulder
        /// Sets the timer.
        /// </summary>
        /// <param name="time">time left in the game.</param>
        /// <param name="totalTime">Total amount of time in the game.</param>
        void setTimer(int time, int totalTime);
        /// <summary>
        /// sets the player name
        /// </summary>
        /// <param name="name"></param>
        void setName(string name1, string name2);
        void gotData();
        void EnablePlay();
        void DisablePlay(); 
        void SetOppposingWords(string[] words);
        /// <summary>
        /// fires when the user wants to play a word. 
        /// parameter is played word, server
        /// </summary>
        event Action<string,string> PlayWord;
        /// <summary>
        /// Creates a user with a nickname.
        /// Parameter is the user nickname and the Server; 
        /// </summary>
        event Action<string, string,int> CreateUser; 
        /// <summary>
        /// Searches for a game, parameter is user nickname and requested time limit
        /// </summary>
        event Action<string,string,int> JoinGame;
        /// <summary>
        /// cancels the game search. 
        /// </summary>
        event Action<string> CancelJoinGame;
        /// <summary>
        /// Event raised by the forms timer
        /// </summary>
        event Action<bool,string> Update;



    }
}