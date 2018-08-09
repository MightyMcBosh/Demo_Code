using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace BoggleClient
{
    public partial class JoinGameDialog : Form
    {

        //fields
        string name;
        string server;
        string time;
        
        IBoggleClient boggle;
        int timerInt;
        public JoinGameDialog(IBoggleClient client)
        {
            InitializeComponent();
            //get the sender
            boggle = client; 
            
        }

        private void nameLabel_Click(object sender, EventArgs e)
        {

        }

        private void nameInput_TextChanged(object sender, EventArgs e)
        {
            name = nameInput.Text.Trim(); 
        }

        private void serverInput_TextChanged(object sender, EventArgs e)
        {
            server = serverInput.Text.Trim();
        }

      

        private void cancelButton_Click(object sender, EventArgs e)
        {
            this.Close(); 
        }

        private void joinButton_Click(object sender, EventArgs e)
        {
            if(server == "" || server == null)
            {
                server = @"http://ice.eng.utah.edu/";
            }
            
            ((BoggleClient)boggle).JoinGameClick(name, server, 0);
            
            this.Close(); 
        }
    }
}
