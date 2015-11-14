import javax.swing.*;
import java.awt.*;

public class Main {

	public static void main(String[] args) {
		
		userauthentication(); // Perform User Authentication if successful then execute the rest else exit
		Car car = new Car ();
		Phone phone = new Phone (car);
	}
	
	/// This function performs the user authentication. It asks the user for password and checks it. If Password tries are >= 3 then the program gets locked
	public static void userauthentication ()
	{
		int passwordtries = 0;
        JPasswordField passwordinput = new JPasswordField ();
        JPanel passwordaskdialog = new JPanel ((new GridLayout (2, 1)));
        passwordaskdialog.add (new JLabel ("Enter the password to access the car"));
        passwordaskdialog.add (passwordinput);
        int userinput;
        while (!passwordinput.getText().equals("Mec2015") && passwordtries < 3)
        {
            passwordinput.setText ("");
            userinput = JOptionPane.showConfirmDialog (null, passwordaskdialog, "Authentication Required", JOptionPane.DEFAULT_OPTION);
            if (!passwordinput.getText().equals("Mec2015"))
            {
                Object[] options = {"Try Again", "Cancel"};  //Options to click in the dialog box
                int passwordfaildialog = JOptionPane.showOptionDialog (null, "User Authentication Failed", "Warning",  //Message to display in the dialog box.
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                        null, options, options [0]);
                if (passwordfaildialog == 1)
                    System.exit (0);
                passwordtries++;
            }
        }
        if (passwordtries >= 3)
        {
            JOptionPane.showMessageDialog (null, "Too many unsuccessful attempts. Program Locked", "Alert",
                    JOptionPane.ERROR_MESSAGE); // Dialog box to display the message if the passwordtries or guesses are more than 3
            System.exit (0);
        }
        else
        { //Begins else loop
            //if they are out of the loop, they got the password
            //Show a dialog that displays the message, 'User Authentication Successful. Click OK to begin':
            Object[] options = {"OK"}; //Options to click in the dialog box
            int successfuldialog = JOptionPane.showOptionDialog (null, "User Authentication Successful. Click OK to begin", "Successful",  //Dialog box.
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options [0]);
        }
	}
}