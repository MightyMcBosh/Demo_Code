namespace BoggleClient
{
    partial class BoggleClient
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.program_label = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.serverStatusLabel = new System.Windows.Forms.Label();
            this.player1Name = new System.Windows.Forms.Label();
            this.player2Name = new System.Windows.Forms.Label();
            this.serverStatus = new System.Windows.Forms.Label();
            this.player1Score = new System.Windows.Forms.Label();
            this.player2Score = new System.Windows.Forms.Label();
            this.bogglePanel = new System.Windows.Forms.Panel();
            this.playTextBox = new System.Windows.Forms.TextBox();
            this.playButton = new System.Windows.Forms.Button();
            this.wordList = new System.Windows.Forms.RichTextBox();
            this.wordListLabel = new System.Windows.Forms.Label();
            this.joinButton = new System.Windows.Forms.Button();
            this.cancelButton = new System.Windows.Forms.Button();
            this.timerPanel = new System.Windows.Forms.Panel();
            this.label2 = new System.Windows.Forms.Label();
            this.timer = new System.Windows.Forms.Timer(this.components);
            this.ErrorLabel = new System.Windows.Forms.Label();
            this.timerLabel = new System.Windows.Forms.Label();
            this.OpponentWords = new System.Windows.Forms.RichTextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.newGameButton = new System.Windows.Forms.Button();
            this.TimeBox = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.HelpButton = new System.Windows.Forms.Button();
            this.timerPanel.SuspendLayout();
            this.SuspendLayout();
            // 
            // program_label
            // 
            this.program_label.AutoSize = true;
            this.program_label.Font = new System.Drawing.Font("Microsoft Sans Serif", 24F);
            this.program_label.Location = new System.Drawing.Point(489, 9);
            this.program_label.Name = "program_label";
            this.program_label.Size = new System.Drawing.Size(116, 37);
            this.program_label.TabIndex = 1;
            this.program_label.Text = "Boggle";
            // 
            // label1
            // 
            this.label1.Location = new System.Drawing.Point(0, 0);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(100, 23);
            this.label1.TabIndex = 2;
            // 
            // serverStatusLabel
            // 
            this.serverStatusLabel.AutoSize = true;
            this.serverStatusLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F);
            this.serverStatusLabel.Location = new System.Drawing.Point(451, 62);
            this.serverStatusLabel.Name = "serverStatusLabel";
            this.serverStatusLabel.Size = new System.Drawing.Size(77, 13);
            this.serverStatusLabel.TabIndex = 1;
            this.serverStatusLabel.Text = "Server Status: ";
            // 
            // player1Name
            // 
            this.player1Name.AutoSize = true;
            this.player1Name.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F);
            this.player1Name.Location = new System.Drawing.Point(146, 55);
            this.player1Name.Name = "player1Name";
            this.player1Name.Size = new System.Drawing.Size(31, 13);
            this.player1Name.TabIndex = 1;
            this.player1Name.Text = "none";
            this.player1Name.TextAlign = System.Drawing.ContentAlignment.TopRight;
            // 
            // player2Name
            // 
            this.player2Name.AutoSize = true;
            this.player2Name.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F);
            this.player2Name.Location = new System.Drawing.Point(316, 55);
            this.player2Name.Name = "player2Name";
            this.player2Name.Size = new System.Drawing.Size(31, 13);
            this.player2Name.TabIndex = 1;
            this.player2Name.Text = "none";
            this.player2Name.Click += new System.EventHandler(this.player2Name_Click);
            // 
            // serverStatus
            // 
            this.serverStatus.AutoSize = true;
            this.serverStatus.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F);
            this.serverStatus.ForeColor = System.Drawing.Color.Maroon;
            this.serverStatus.Location = new System.Drawing.Point(516, 62);
            this.serverStatus.Name = "serverStatus";
            this.serverStatus.Size = new System.Drawing.Size(71, 13);
            this.serverStatus.TabIndex = 1;
            this.serverStatus.Text = "disconnected";
            this.serverStatus.TextChanged += new System.EventHandler(this.serverStatus_TextChanged);
            // 
            // player1Score
            // 
            this.player1Score.AutoSize = true;
            this.player1Score.Font = new System.Drawing.Font("Microsoft Sans Serif", 24F);
            this.player1Score.Location = new System.Drawing.Point(174, 37);
            this.player1Score.Name = "player1Score";
            this.player1Score.Size = new System.Drawing.Size(35, 37);
            this.player1Score.TabIndex = 1;
            this.player1Score.Text = "0";
            // 
            // player2Score
            // 
            this.player2Score.AutoSize = true;
            this.player2Score.Font = new System.Drawing.Font("Microsoft Sans Serif", 24F);
            this.player2Score.Location = new System.Drawing.Point(285, 37);
            this.player2Score.Name = "player2Score";
            this.player2Score.Size = new System.Drawing.Size(35, 37);
            this.player2Score.TabIndex = 1;
            this.player2Score.Text = "0";
            // 
            // bogglePanel
            // 
            this.bogglePanel.Location = new System.Drawing.Point(66, 77);
            this.bogglePanel.Name = "bogglePanel";
            this.bogglePanel.Size = new System.Drawing.Size(372, 344);
            this.bogglePanel.TabIndex = 3;
            this.bogglePanel.Paint += new System.Windows.Forms.PaintEventHandler(this.bogglePanel_Paint);
            // 
            // playTextBox
            // 
            this.playTextBox.Location = new System.Drawing.Point(66, 455);
            this.playTextBox.Name = "playTextBox";
            this.playTextBox.Size = new System.Drawing.Size(241, 20);
            this.playTextBox.TabIndex = 4;
            this.playTextBox.KeyDown += new System.Windows.Forms.KeyEventHandler(this.playTextBox_KeyDown);
            // 
            // playButton
            // 
            this.playButton.Location = new System.Drawing.Point(319, 453);
            this.playButton.Name = "playButton";
            this.playButton.Size = new System.Drawing.Size(75, 23);
            this.playButton.TabIndex = 5;
            this.playButton.Text = "Play!";
            this.playButton.UseVisualStyleBackColor = true;
            this.playButton.Click += new System.EventHandler(this.playButton_Click);
            // 
            // wordList
            // 
            this.wordList.Cursor = System.Windows.Forms.Cursors.Arrow;
            this.wordList.Enabled = false;
            this.wordList.Location = new System.Drawing.Point(444, 100);
            this.wordList.Name = "wordList";
            this.wordList.Size = new System.Drawing.Size(68, 321);
            this.wordList.TabIndex = 6;
            this.wordList.Text = "";
            // 
            // wordListLabel
            // 
            this.wordListLabel.AutoSize = true;
            this.wordListLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F);
            this.wordListLabel.Location = new System.Drawing.Point(460, 84);
            this.wordListLabel.Name = "wordListLabel";
            this.wordListLabel.Size = new System.Drawing.Size(52, 13);
            this.wordListLabel.TabIndex = 1;
            this.wordListLabel.Text = "Word List";
            // 
            // joinButton
            // 
            this.joinButton.Location = new System.Drawing.Point(12, 8);
            this.joinButton.Name = "joinButton";
            this.joinButton.Size = new System.Drawing.Size(75, 23);
            this.joinButton.TabIndex = 8;
            this.joinButton.Text = "Join Game";
            this.joinButton.UseVisualStyleBackColor = true;
            this.joinButton.Click += new System.EventHandler(this.joinButton_Click);
            // 
            // cancelButton
            // 
            this.cancelButton.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.cancelButton.Enabled = false;
            this.cancelButton.Location = new System.Drawing.Point(12, 37);
            this.cancelButton.Name = "cancelButton";
            this.cancelButton.Size = new System.Drawing.Size(75, 23);
            this.cancelButton.TabIndex = 8;
            this.cancelButton.Text = "Cancel";
            this.cancelButton.UseVisualStyleBackColor = true;
            this.cancelButton.Click += new System.EventHandler(this.cancelButton_Click);
            // 
            // timerPanel
            // 
            this.timerPanel.Controls.Add(this.label2);
            this.timerPanel.Location = new System.Drawing.Point(228, 30);
            this.timerPanel.Margin = new System.Windows.Forms.Padding(1);
            this.timerPanel.Name = "timerPanel";
            this.timerPanel.Size = new System.Drawing.Size(42, 42);
            this.timerPanel.TabIndex = 9;
            this.timerPanel.Paint += new System.Windows.Forms.PaintEventHandler(this.timerPanel_Paint);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(3, 17);
            this.label2.Margin = new System.Windows.Forms.Padding(1, 0, 1, 0);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(0, 13);
            this.label2.TabIndex = 0;
            // 
            // timer
            // 
            this.timer.Interval = 1000;
            this.timer.Tick += new System.EventHandler(this.timer_Tick);
            // 
            // ErrorLabel
            // 
            this.ErrorLabel.AutoSize = true;
            this.ErrorLabel.Location = new System.Drawing.Point(496, 469);
            this.ErrorLabel.Name = "ErrorLabel";
            this.ErrorLabel.Size = new System.Drawing.Size(0, 13);
            this.ErrorLabel.TabIndex = 10;
            // 
            // timerLabel
            // 
            this.timerLabel.AutoSize = true;
            this.timerLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F);
            this.timerLabel.Location = new System.Drawing.Point(240, 16);
            this.timerLabel.Name = "timerLabel";
            this.timerLabel.Size = new System.Drawing.Size(19, 13);
            this.timerLabel.TabIndex = 1;
            this.timerLabel.Text = "60";
            this.timerLabel.Click += new System.EventHandler(this.player2Name_Click);
            // 
            // OpponentWords
            // 
            this.OpponentWords.Cursor = System.Windows.Forms.Cursors.Arrow;
            this.OpponentWords.Enabled = false;
            this.OpponentWords.Location = new System.Drawing.Point(519, 100);
            this.OpponentWords.Name = "OpponentWords";
            this.OpponentWords.Size = new System.Drawing.Size(68, 321);
            this.OpponentWords.TabIndex = 6;
            this.OpponentWords.Text = "";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F);
            this.label3.Location = new System.Drawing.Point(533, 84);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(54, 13);
            this.label3.TabIndex = 1;
            this.label3.Text = "Opponent";
            this.label3.Click += new System.EventHandler(this.label3_Click);
            // 
            // newGameButton
            // 
            this.newGameButton.Enabled = false;
            this.newGameButton.Location = new System.Drawing.Point(292, 3);
            this.newGameButton.Name = "newGameButton";
            this.newGameButton.Size = new System.Drawing.Size(75, 23);
            this.newGameButton.TabIndex = 8;
            this.newGameButton.Text = "Find Game";
            this.newGameButton.UseVisualStyleBackColor = true;
            this.newGameButton.Click += new System.EventHandler(this.newGameButton_Click);
            // 
            // TimeBox
            // 
            this.TimeBox.Location = new System.Drawing.Point(427, 5);
            this.TimeBox.Name = "TimeBox";
            this.TimeBox.Size = new System.Drawing.Size(33, 20);
            this.TimeBox.TabIndex = 11;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F);
            this.label4.Location = new System.Drawing.Point(373, 8);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(48, 13);
            this.label4.TabIndex = 1;
            this.label4.Text = "of length";
            this.label4.Click += new System.EventHandler(this.player2Name_Click);
            // 
            // HelpButton
            // 
            this.HelpButton.Location = new System.Drawing.Point(1, 457);
            this.HelpButton.Name = "HelpButton";
            this.HelpButton.Size = new System.Drawing.Size(48, 25);
            this.HelpButton.TabIndex = 12;
            this.HelpButton.Text = "Help";
            this.HelpButton.UseVisualStyleBackColor = true;
            this.HelpButton.Click += new System.EventHandler(this.HelpButton_Click);
            // 
            // BoggleClient
            // 
            this.AcceptButton = this.joinButton;
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.CancelButton = this.CancelButton;
            this.ClientSize = new System.Drawing.Size(599, 488);
            this.Controls.Add(this.HelpButton);
            this.Controls.Add(this.TimeBox);
            this.Controls.Add(this.ErrorLabel);
            this.Controls.Add(this.timerPanel);
            this.Controls.Add(this.newGameButton);
            this.Controls.Add(this.cancelButton);
            this.Controls.Add(this.joinButton);
            this.Controls.Add(this.OpponentWords);
            this.Controls.Add(this.wordList);
            this.Controls.Add(this.playButton);
            this.Controls.Add(this.playTextBox);
            this.Controls.Add(this.bogglePanel);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.wordListLabel);
            this.Controls.Add(this.timerLabel);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.player2Name);
            this.Controls.Add(this.player1Name);
            this.Controls.Add(this.serverStatus);
            this.Controls.Add(this.serverStatusLabel);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.player2Score);
            this.Controls.Add(this.player1Score);
            this.Controls.Add(this.program_label);
            this.MaximumSize = new System.Drawing.Size(615, 527);
            this.MinimumSize = new System.Drawing.Size(609, 382);
            this.Name = "BoggleClient";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.BoggleClient_Load);
            this.timerPanel.ResumeLayout(false);
            this.timerPanel.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.Label program_label;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label serverStatusLabel;
        private System.Windows.Forms.Label player1Name;
        private System.Windows.Forms.Label player2Name;
        private System.Windows.Forms.Label serverStatus;
        private System.Windows.Forms.Label player1Score;
        private System.Windows.Forms.Label player2Score;
        private System.Windows.Forms.Panel bogglePanel;
        private System.Windows.Forms.TextBox playTextBox;
        private System.Windows.Forms.Button playButton;
        private System.Windows.Forms.RichTextBox wordList;
        private System.Windows.Forms.Label wordListLabel;
        private System.Windows.Forms.Button joinButton;
        private System.Windows.Forms.Button cancelButton;
        private System.Windows.Forms.Panel timerPanel;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Timer timer;
        private System.Windows.Forms.Label ErrorLabel;
        private System.Windows.Forms.Label timerLabel;
        private System.Windows.Forms.RichTextBox OpponentWords;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Button newGameButton;
        private System.Windows.Forms.TextBox TimeBox;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Button HelpButton;
    }
}

