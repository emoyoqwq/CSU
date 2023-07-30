# Development Manual
***
### Setting Up an IntelliJ Environment
1. Find the required versions of NPM, Java, Maven, and Node that are used on the CSU lab machines. Your development environment must match the CSU lab machines.
   * Once logged into a CSU machine, use the -v or --version options to determine what versions are used on the CSU lab machines.
2. Install the versions that match the CSU lab machines on your local machine. 
3. Add an SSH key to GitHub for logging in. Instructions can be found at [this GitHub page](https://docs.github.com/en/authentication/connecting-to-github-with-ssh/adding-a-new-ssh-key-to-your-github-account).
4. Start a new project using a version manager in IntelliJ to clone the proper git repository. 
   * Make sure to choose the Java version that you installed earlier.
5. Open a terminal window in IntelliJ and navigate to the client directory.
6. Run the command ```npm install``` to install all the required dependencies for this particular development environment.
7. In the terminal, navigate to the 'bin' directory and execute 'run.sh' in your terminal.

### Setting Up an VS Code Environment
1. Download and open VS Code.
2. In the Extensions tab, download Debugger for Chrome and Remote - SSH.
3. In the bottom left corner, click the green button to open a remote window.
4. Connect the current window to the host by entering *username*@*csmachine*.cs.colostate.edu in the text box that appears 
   * *Ex: johndoe@trenton.cs.colostate.edu*
5. Enter your CS password that you use for the lab machines.
6. Install the required versions of NPM, Java, Maven, and Node that are used on the CSU lab machines.
7. Open a terminal window in VS Code.
8. SSH into the lab machines with your username and password.
9. Make a new directory and navigate to that directory in the Terminal.
10. Once in that directory, clone the proper git repository using the terminal.
11. Run the command ```npm install``` to install all the required dependencies for this particular development environment.
12. In the terminal, navigate to the 'bin' directory and execute 'run.sh' in your terminal.

### Accessing the Database
1. Log into CS lab machines using SSH 
   * Log in using the ssh command to your preferred CS machine. 
   * Note: You may need to setup port forwarding using the [Port Forwarding](#port-forwarding) instructions. 
2. Connect to database using the following command:
```
$ mysql -u <EID_NAME> -D cs414_team11 -h faure -p
```
3. The password is your student ID number.

### Port Forwarding
1. Edit your SSH configuration file to include the following
```
Host cs414
    Hostname <CHOOSE_YOUR_CS_LAB_MACHINE>.cs.colostate.edu
    user <YOUR_EID_USERNAME>
    LocalForward 56247 faure:3306
    LocalForward 41411 black-bottle:41411
```
2. For additional information, see the [CS314 Port Forwarding Page](https://www.cs.colostat.edu/~cs314/status/#/forwarding).
