using System;
using System.Drawing;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace BoggleClient
{
    public partial class BoggleClient : Form, IBoggleClient

    {
        Graphics g;
        Graphics timerG;
        public string nickname;
        public string server;
        private int time;
        private int totalTime = 60;
        private bool GameState;
        private string test = "abcdefghijklmnop";
        public bool DoesHaveData = false;
        public event Action<string, string> PlayWord;
        public event Action<string, string, int> CreateUser;
        public event Action<string, string, int> JoinGame;
        public event Action<string> CancelJoinGame;
        public event Action<bool, string> Update;
        public BoggleClient()
        {
            InitializeComponent();
            g = bogglePanel.CreateGraphics();
            nickname = "";
            server = "";
            time = -1;
            GameState = false;
            timerG = timerPanel.CreateGraphics();
        }



        public void drawBoard(string boggleLetters)
        {
            g.Clear(Color.AntiqueWhite); 
            Font LetterFont = new Font(FontFamily.GenericSansSerif, 32);

            if (boggleLetters.Length != 16)
                MessageBox.Show("Boggle board invalid");

            boggleLetters = boggleLetters.ToUpper();

            for (int i = 0; i < 16; i++)
            {
                g.FillRectangle(Brushes.LightBlue, (i % 4) * 90, ((int)(i / 4)) * 90, 80, 80);
                if (boggleLetters[i] == 'Q')
                {
                    g.DrawString("Qu", LetterFont, Brushes.Black, new Point((i % 4) * 90, ((int)(i / 4)) * 90 + 13));
                }
                else
                    g.DrawString(boggleLetters[i].ToString(), LetterFont, Brushes.Black, new Point((i % 4) * 90 + 17, ((int)(i / 4)) * 90 + 13));
            }

            
            


        }


        private void textBox2_TextChanged(object sender, EventArgs e)
        {

        }

        private void player2_value_TextChanged(object sender, EventArgs e)
        {
        }

        private static void errorMsg(string msg)
        {
            MessageBox.Show(msg);
        }

        private void bogglePanel_Paint(object sender, PaintEventArgs e)
        {

        }

        public void setScore(int player, int score)
        {
            if (player == 1)
                player1Score.Text = score.ToString();
            else if (player == 2)
                player2Score.Text = score.ToString();
        }

        public void setConnectionStatus(string code)
        {
            serverStatus.Text = code;
        }

        public void setWordsPlayed(string[] words)
        {
            wordList.Clear();
            wordList.Lines = words;
        }

        private void playTextBox_KeyDown(object sender, KeyEventArgs e)
        {
            string value = playTextBox.Text;

            if (e.KeyCode == Keys.Enter && GameState == true)
            {
                // errorMsg("press");

                PlayWord?.Invoke(value, server);
            }
        }



        public void EnablePlay()
        {
            newGameButton.Enabled = true;
            joinButton.Enabled = true;
        }
        public void DisablePlay()
        {
            newGameButton.Enabled = false;
        }

        private void playButton_Click(object sender, EventArgs e)
        {
            string value = playTextBox.Text;
            playTextBox.Clear();
            PlayWord?.Invoke(value, server);
        }

        private void BoggleClient_Load(object sender, EventArgs e)
        {

        }
        public void JoinGameClick(string name, string server, int time)
        {
            this.server = server;
            
            CreateUser?.Invoke(name, server, time);

        }

        private void joinButton_Click(object sender, EventArgs e)
        {
            JoinGameDialog jgd = new JoinGameDialog(this);
            jgd.Show();
            //if(serverStatus.Text != "disconnected")
            joinButton.Enabled = false;

            cancelButton.Enabled = true;
        }

        private void timerPanel_Paint(object sender, PaintEventArgs e)
        {
            SolidBrush timerBrush = new SolidBrush(Color.Green);
            Rectangle timerRect = new Rectangle(0, 0, timerPanel.Size.Height - 5, timerPanel.Size.Width - 5);
            float startAngle = -90.0F;
            //  float sweepAngle = -300.0F;
            float sweepAngle = -((float)time / (float)totalTime * 360); //TODO: Grab the live updated time from the controller once implemented

            timerG.FillPie(timerBrush, timerRect, startAngle, sweepAngle);
        }

        public void setTimer(int time, int totalTime)
        {
            this.time = time;
            this.totalTime = totalTime;
            timerPanel.Refresh();
            timerPanel.BringToFront();

            timerLabel.Text = time.ToString();
        }

        public void setName(string name1, string name2)
        {
            player1Name.Text = name1;
            player2Name.Text = name2;
        }

        private void serverStatus_TextChanged(object sender, EventArgs e)
        {
            if (serverStatus.Text != "disconnected")
            {
                joinButton.Enabled = false;
                if (serverStatus.Text != "pending")
                    playButton.Enabled = true;
                timer.Enabled = true;
                cancelButton.Enabled = true;
                

            }
            else
            {
                joinButton.Enabled = true;
                
                playButton.Enabled = false;
                timer.Enabled = false;
                cancelButton.Enabled = false;
            }

       

            if (serverStatus.Text == "active")
            {
                this.AcceptButton = playButton;

            }

            else if (serverStatus.Text == "disconnected")
            {
                this.AcceptButton = joinButton;

            }

            if (serverStatus.Text == "completed")
            {
                DoesHaveData = false;
                newGameButton.Enabled = true;
            }
        }

        private void timer_Tick(object sender, EventArgs e)
        {

            Update?.Invoke(DoesHaveData, server);
            //DoesHaveData = true;
        }

        private void cancelButton_Click(object sender, EventArgs e)
        {
            serverStatus.Text = "disconnected"; 
            CancelJoinGame?.Invoke(server);
            joinButton.Enabled = true;
            newGameButton.Enabled = true;
            

        }
        public void setError(string msg)
        {
            ErrorLabel.Text = msg;
        }

        public void gotData()
        {
            DoesHaveData = true;
        }

        private void player2Name_Click(object sender, EventArgs e)
        {

        }

        private void label3_Click(object sender, EventArgs e)
        {

        }

        public void SetOppposingWords(string[] words)
        {
            OpponentWords.Clear();
            OpponentWords.Lines = words;
        }

        private void newGameButton_Click(object sender, EventArgs e)
        {
            int parse = 0 ;
            DoesHaveData = false;
             if(int.TryParse(TimeBox.Text, out parse) && parse >= 5 && parse < 120)
                JoinGame?.Invoke(nickname, server, parse);
            else
                JoinGame?.Invoke(nickname, server, 60);

            newGameButton.Enabled = false;
        }

        private void HelpButton_Click(object sender, EventArgs e)
        {
            MessageBox.Show("To register on a server, click join game. Enter your nickname and the server url if you have one. \r\n if you dont, leave it blank it it will default to ice.eng.utah.edu.\r\n then type in the amount of time you want the game to last into the time box (or leave blank, will default to 60 seconds)     and click find game. \r\n \r\n To play, type your word into the bottom text box and click Play! or hit enter. \r\n the server will score your word and it will print into your words window. \r\n after the game is over, click Join to get into a differnt server or click find game to play again.");
        }
    }
}
