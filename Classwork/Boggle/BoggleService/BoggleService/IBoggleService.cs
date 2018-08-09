using System.Collections.Generic;
using System.IO;
using System.ServiceModel;
using System.ServiceModel.Web;

namespace Boggle
{
    [ServiceContract]
    public interface IBoggleService
    {
        /// <summary>
        /// Sends back index.html as the response body.
        /// </summary>
        [WebGet(UriTemplate = "/api")]
        Stream API();

        /// <summary>
        /// Creates a new user with the specified nickname and returns a unique user hash that is used for all subsequent communication.
        /// </summary>
        /// <param name="nickname">Nickname of the user</param>
        /// <returns></returns>
        [WebInvoke(Method = "POST", UriTemplate = "/users")]
        User CreateUser(NewUser user);

        /// <summary>
        /// 
       /// If UserToken is invalid, TimeLimit 120, responds with status 403 (Forbidden).
        ///Otherwise, if UserToken is already a player in the pending game, responds with status 409 (Conflict).
        ///Otherwise, if there is already one player in the pending game, adds UserToken as the second player.The pending game becomes active and a new pending game with no players is created.
        ///The active game's time limit is the integer average of the time limits requested by the two players. Returns the new active game's GameID(which should be the same as the old pending game's GameID). Responds with status 201 (Created).
        ///
        ///  Otherwise, adds UserToken as the first player of the pending game, and the TimeLimit as the pending game's requested time limit. Returns the pending game's GameID. Responds with status 202 (Accepted).

        /// </summary>
        /// <param name="UserToken">Token of the user joining the game</param>
        /// <param name="TimeLimit">Requested time limit</param>
        /// <returns>A string of the game id </returns>
        [WebInvoke(BodyStyle = WebMessageBodyStyle.Bare,Method = "POST", UriTemplate = "/games")]
        ReturnGame JoinGame(NewGame _game);
        /// <summary>
        ///  Cancel a pending request to join a game.

        ///If UserToken is invalid or is not a player in the pending game, responds with status 403 (Forbidden).
        ///Otherwise, removes UserToken from the pending game and responds with status 200 (OK).

        /// </summary>
        /// <param name="UserToken">User to be removed from queue</param>
        [WebInvoke(Method = "PUT", UriTemplate = "/games")]
        void CancelJoinRequest(User UserToken);
        /// <summary>
        ///  Play a word in a game.
        ///If Word is null or empty when trimmed, or if GameID or UserToken is missing or invalid, or if UserToken is not 
        ///a player in the game identified by GameID, responds with response code 403 (Forbidden).
        ///Otherwise, if the game state is anything other than "active", responds with response code 409 (Conflict).
        ///Otherwise, records the trimmed Word as being played by UserToken in the game identified by GameID.
        ///Returns the score for Word in the context of the game(e.g. if Word has been played before the score is zero). Responds with status 200 (OK). Note: The word is not case sensitive.

        /// </summary>
        /// <param name="UserToken">User</param>
        /// <param name="Word">Word to be played</param>
        /// <param name="GameID">Game id in question</param>
        /// <returns>the score oF the word</returns>
        [WebInvoke(BodyStyle = WebMessageBodyStyle.WrappedRequest,Method = "PUT", UriTemplate = "/games/{GameID}")]
        _Score Score(string UserToken, string Word, string GameID);
        //backup for safety, breaking things CODE:001
        //[WebInvoke(BodyStyle = WebMessageBodyStyle.Wrapped, Method = "PUT", UriTemplate = "/games/{GameID}")]
        //Score playWord(NewWord _word, string GameID);


        /// <summary>
        ///  Get game status information.

        ///If GameID is invalid, responds with status 403 (Forbidden).
        /// Otherwise, returns information about the game named by GameID as illustrated below.
        /// Note that the information returned depends on whether "Brief=yes" was included as a parameter as well as on the state of the game. Responds with status code 200 (OK). Note: The Board and Words are not case sensitive.
        /* Status: 200 OK
         {
         "GameState": "active",
         "Board": "ANETIXSRETAPLMON",
         "TimeLimit": 120,
         "TimeLeft": 32,
         "Player1": {
             "Nickname": "Jack",
             "Score": 3,
         },
         "Player2": {
             "Nickname": "Jill",
             "Score": 1,
         },
         }*/

        /// </summary>
        /// <param name="brief"></param>
        /// <param name="GameID"></param>
        /// <returns></returns>
        [WebGet(UriTemplate = "/games/{GameID}?Brief={brief}")]
        Ret4 GameStatus(string brief,  string GameID);
    }
}
