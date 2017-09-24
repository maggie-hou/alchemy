/**
 * @(#)Alchemy.java
 *
 * Alchemy Game application
 *
 * @Maggie Hou
 * @Start 1.00 2015/4/19
 * @End 2015/6/03
 */
 import java.io.*; 
 import java.util.*; 
 import javax.swing.*; 
 import java.awt.*; 
 import java.awt.event.*; 
 
public class maggie_Alchemy implements MouseListener{
    
    boolean tracing=false; 
    
    JFrame mainFrame= new JFrame("Alchemy"); 
    JPanel title=new JPanel(new BorderLayout()); 
    JPanel playPane=new JPanel(new BorderLayout()); 
	JPanel titleButtons= new JPanel(new GridLayout(1, 2)); 
	JButton start=new JButton ("START");
	JButton ins=new JButton("INSTRUCTIONS"); 
	
	JPanel disPanel=new JPanel(new BorderLayout()); 	
	JButton discard=new JButton("Discard"); 
		
	Toolkit toolkit = Toolkit.getDefaultToolkit(); 
    
    ImageIcon unchosenSquare = new ImageIcon ("unchosenSquare.jpg");
    JLabel[][] gameDisplayArray = new JLabel [8][8]; 
	JPanel gameDisplayPanel= new JPanel(new GridLayout(7,7)); 
	JPanel sidePanel=new JPanel(new GridLayout(8, 1) ); 
		
	JPanel runePane=new JPanel(new BorderLayout()); 
	JLabel rune=new JLabel(); 
	JLabel topSpace; 
	JLabel scoreLabel=new JLabel(); 
	
	
	int[][]symbol=new int[9][9]; 
	char [][] color=new char[9][9]; 
	boolean [][] turn=new boolean[9][9]; 
	boolean[][] has=new boolean[9][9]; 
	int curSym=-1; 
	char curCol='x'; 
		
	int end=1; 
	
	JProgressBar progressBar; 
	
	int dis=0; //game start setup
	int level=1; 
	int score=0; 
	
	Point hotSpot = new Point(10,0);
	
	JButton endGame=new JButton("End Game"); //end setup
	JPanel endPane=new JPanel(new BorderLayout()); 
	JPanel endButtonPanel=new JPanel(new GridLayout(1, 2)); 
	JButton replayGame=new JButton("Replay"); 
	JButton exitGame=new JButton("Exit"); 
	JLabel endHighLabel=new JLabel(); 
		
	JPanel instructPane=new JPanel(new BorderLayout()); 
	JButton goHome=new JButton("Return"); 
    	
    public static void main(String[] args) {
    	
	new maggie_Alchemy(); 
    }
    
    maggie_Alchemy(){
    	
    	JLabel titleLabel=new JLabel (new ImageIcon("title.jpg"));//title
    	
	    titleButtons.add(start);
	    titleButtons.add(ins);	
	    title.add(titleLabel, BorderLayout.CENTER); //adding picture title
	    title.add(titleButtons, BorderLayout.SOUTH);//adding buttons pane
	    
	    JLabel instructionLabel=new JLabel(new ImageIcon("instructions.png")); //instructions
	    instructPane.add(goHome, BorderLayout.SOUTH); 
    	instructPane.add(instructionLabel, BorderLayout.CENTER); 
	    
	    start.addMouseListener(this);//mouse listeners
    	ins.addMouseListener(this);
    	goHome.addMouseListener(this); 
    	discard.addMouseListener(this); 
    	endGame.addMouseListener(this); 
    	replayGame.addMouseListener(this); 
    	exitGame.addMouseListener(this); 

    	
    	JPanel topPanel=new JPanel(); //game pane spacing
    	topSpace=new JLabel("<html>pp<br><br><br></html>"); 
    	topPanel.add(topSpace); 
    	JPanel btPanel=new JPanel(); 
    	JLabel btSpace=new JLabel("<html><br><br></html>"); 
    	btPanel.add(btSpace); 
    	JPanel rightPanel=new JPanel(); 
    	JLabel rightSpace=new JLabel("                  "); 
    	rightPanel.add(rightSpace); 
    		
    	Color gold = new Color (209, 201, 46);  //background gold
    	gameDisplayPanel.setBackground(gold);
    	
    	//side bar setup
    	JLabel logo=new JLabel(new ImageIcon("logo.jpg")); 
	   	JLabel sideSpace=new JLabel("                                                                                                                                 "); 
		JLabel current=new JLabel("Your Current Rune", SwingConstants.CENTER); 
		current.setFont(new Font("Helvetica",Font.PLAIN,18)); 
		rune.setIcon(new ImageIcon("blank.png"));
		
		runePane.add(rune, BorderLayout.CENTER);  
		JLabel filler=new JLabel("                                                      "); 
		runePane.add(filler, BorderLayout.WEST); 
		JLabel filler2=new JLabel("                                                      "); 
		runePane.add(filler2, BorderLayout.EAST); 
		
		disPanel.add(discard, BorderLayout.CENTER); //discard button pane setup
		JLabel disFill=new JLabel("                          "); 
		JLabel disFill2=new JLabel("                          "); 
		JLabel disFill3=new JLabel("                          "); 
		disPanel.add(disFill, BorderLayout.EAST); 
		disPanel.add(disFill2, BorderLayout.WEST); 
		disPanel.add(disFill3, BorderLayout.NORTH); 
			
		progressBar = new JProgressBar(0, 100);//progress bar setup
		progressBar.setValue(dis*25);
		progressBar.setStringPainted(true);
		progressBar.setString("0 discarded"); 
			
		JPanel barPanel=new JPanel(new BorderLayout()); //progress pane setup
		barPanel.add(progressBar, BorderLayout.CENTER); 
		JLabel disbFill=new JLabel("                   "); 
		JLabel disbFill2=new JLabel("                   "); 
		JLabel disbFill3=new JLabel("                          "); 
		JLabel disbFill4=new JLabel("                          "); 


		barPanel.add(disbFill, BorderLayout.EAST); 
		barPanel.add(disbFill2, BorderLayout.WEST); 
		barPanel.add(disbFill3, BorderLayout.NORTH); 
		barPanel.add(disbFill4, BorderLayout.SOUTH); 
		
		JPanel scorePanel=new JPanel(new BorderLayout()); 
		JLabel sf=new JLabel("                                                        "); //scorefill

		
		scorePanel.add(sf, BorderLayout.WEST);
		scoreLabel.setFont(new Font("Helvetica",Font.PLAIN,18)); 
		scorePanel.add(scoreLabel, BorderLayout.CENTER); 
		
		JPanel finish=new JPanel(new BorderLayout()); 
		finish.add(endGame, BorderLayout.CENTER); //discard button pane setup
		JLabel disFill1=new JLabel("                          "); 
		JLabel disFill12=new JLabel("                          "); 
		JLabel disFill13=new JLabel("                          "); 
		finish.add(disFill1, BorderLayout.EAST); 
		finish.add(disFill12, BorderLayout.WEST); 
		finish.add(disFill13, BorderLayout.NORTH); 
		
		
		sidePanel.add(logo); //adding to side panel
		sidePanel.add(current); 
		sidePanel.add(runePane);
		sidePanel.add(disPanel); 
		sidePanel.add(barPanel); 
		sidePanel.add(scorePanel); 
		sidePanel.add(finish); 
		sidePanel.add(sideSpace);
		
		
    	
    	
    	playPane.add(gameDisplayPanel, BorderLayout.CENTER); //playPane setup
    	playPane.add(sidePanel,BorderLayout.WEST); 
   		playPane.add(topPanel, BorderLayout.NORTH); 
    	playPane.add(rightPanel,BorderLayout.EAST); 
    	playPane.add(btPanel,BorderLayout.SOUTH); 
    	for(int row=1; row<8; row++)
    		for (int col=1; col<8; col++)
    		{
    			gameDisplayArray[row][col]=new JLabel(unchosenSquare); 
    			gameDisplayPanel.add(gameDisplayArray[row][col]); 
    			gameDisplayArray[row][col].addMouseListener (this);
    		}
    		
    		
    		
    	endButtonPanel.add(replayGame);
    	endButtonPanel.add(exitGame);
    	endHighLabel.setFont(new Font("Helvetica",Font.PLAIN,27)); 
    	JLabel spaceFillerEnd=new JLabel("                                          "); 
    	spaceFillerEnd.setFont(new Font("Helvetica",Font.PLAIN,27)); 
    	endPane.add(spaceFillerEnd, BorderLayout.WEST);
    	endPane.add(endHighLabel, BorderLayout.CENTER); 
    	endPane.add(endButtonPanel, BorderLayout.SOUTH); 
    	
    	
	    mainFrame.setContentPane(title);
    	mainFrame.setSize(942,600);
    	mainFrame.setResizable(false);
    	mainFrame.setLocationRelativeTo(null);
    	mainFrame.setVisible(true);
    	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    
    }
    //---------------------------------------------------------------------------------------------------------------
    public void gameSetup()
    {
    	for (int row=0; row<9; row++)
    		for (int col=0; col<9; col++)
    		{
    			symbol[row][col]=-1;
    			color[row][col]='x'; 
    			turn[row][col]=false; 
    			has[row][col]=false; 
    		}
    	for(int row=1; row<8; row++)
    		for (int col=1; col<8; col++)
    		{
    			gameDisplayArray[row][col].setIcon(new ImageIcon ("unchosenSquare.jpg")); 
    		}
    	topSpace.setFont(new Font("Helvetica",Font.PLAIN,14)); 
    	topSpace.setText("<html><br>Level "+level+"<br><br></html>");
    	scoreLabel.setText("Score: "+score); 
    	gameDisplayArray[4][4].setIcon(new ImageIcon("rock.png"));
    	gameDisplayArray[4][4].setOpaque(false); 
    	symbol[4][4]=-2;
    	color[4][4]='r'; 
    	turn[4][4]=true; 
    	has[4][4]=true;
    	if (tracing)System.out.println(symbol[4][4]);
    	
    	curSym=Math.abs(genSym()); 
    	curCol=genCol(); 
    	
    	end=1; 
    	dis=0; 
    	
    	progressBar.setValue(dis*25);
    	progressBar.setString("0 discarded"); 
    	
    	Image charm = toolkit.getImage(curSym+"cur.png");    									
		Cursor use = toolkit.createCustomCursor(charm , hotSpot, "symbol");
		rune.setIcon(new ImageIcon(curSym+".png"));
		rune.setOpaque(true);
		rune.setBackground(directColor(curCol)); 
		playPane.setCursor (use);
    		

    }
    public int genSym()
    {
    	int sym=1; 
    	int type=(int)(Math.random()*22+1);
    	int num=levelSymDirect(level); 
    	if (tracing) System.out.println("Level Symbol: "+num); 
    	if (tracing) System.out.println("type: "+type);
    	switch (type)
    	{
    		case 1:
    		case 22:
    		case 3:
    		case 4:
    		case 5:
    		case 6:
    		case 7:
    		case 8:
    		case 9:
    		case 19:
    		case 11:
    		case 20:
    		case 13:
    		case 14:
    		case 15:
    		case 16:
    		case 17:
    		case 18:
    			sym=(int)(Math.random()*num+1); 
    			break; 
    		case 10:
    			sym=-2;
    			break;
    		case 12:
    		case 21:
    			sym=-3; 
    			break;
    		case 2:
    			sym=-4;
    			break; 

    	}
    	
    	if (tracing) System.out.println("chose Sym: "+sym);
    	return sym; 
    }
    public char genCol()
    {
    	int num=levelColDirect(level); 
    	if (tracing) System.out.println("Level Color: "+num); 
    	int uni=(int)( Math.random()*num+65);
    	char col=(char)uni; 
    	if (tracing) System.out.println("chose Col: "+col);
    	return col; 
    }
    
    public void endSequence(String name)
    {
    	String allScores=""; //getting scores from file
		try
		{
			BufferedReader sc=new BufferedReader(new FileReader("high.txt")); 
			allScores=sc.readLine(); 
			sc.close(); 
		}
		catch (Exception e)
		{
			System.out.println("file error"); 
			mainFrame.setContentPane(title); 
    		level=1; 
    		score=0; 
    		gameSetup(); 
    		mainFrame.validate();
    		mainFrame.repaint();
		}
		String[] ascend=allScores.split(" ");
		
		
    	try
		{
			if (name.equals("null"))name="N/A"; 
			PrintWriter high=new PrintWriter(new FileWriter("high.txt", true)); 
			if(score!=0)
			{
				
				int entries=ascend.length; 
				String tagNum=""; 
				if(entries<1000)
				{
					tagNum="0"+tagNum; 
				}
				if(entries<100)
				{
					tagNum="0"+tagNum; 
				}
				if(entries<10)
				{
					tagNum="0"+tagNum; 
				}
				if (tracing) System.out.println("tagNumber: "+tagNum); 
				high.print(" "+score+tagNum+entries+"-"+name); 
			}
			high.close();
		}
		catch (Exception e)
		{
			System.out.println("file error"); 
			mainFrame.setContentPane(title); 
    		level=1; 
    		score=0; 
    		gameSetup(); 
    		mainFrame.validate();
    		mainFrame.repaint();
		}
		
		int [] onlyScores=new int[ascend.length]; 
		
		for (int i=0; i<onlyScores.length; i++)
		{
			onlyScores[i]=Integer.parseInt(ascend[i].substring(0, ascend[i].indexOf('-'))); 
			if (tracing) System.out.println("onlyScores: "+onlyScores[i]); 
		}
		
		
		Arrays.sort(onlyScores); 
		
		for (int i=0; i<onlyScores.length; i++)
		{
			if (tracing) System.out.println("onlyScores after Sort: "+onlyScores[i]); 
		}
		
		int [] rawScores=new int[onlyScores.length]; 
		
		for (int i=0; i<onlyScores.length; i++)
		{
			rawScores[i]=onlyScores[i]/10000; 
			if (tracing) System.out.println("rawscores: "+rawScores[i]); 
		}
			
		int [] temp=onlyScores; 	
		if (tracing) System.out.println("onlyScoreslength: "+onlyScores.length); 
		for (int x=0; x<5; x++)
		{
			onlyScores[x]=temp[temp.length-x-1]; 
			if(tracing) System.out.println("indice: "+(temp.length-x-1));
			if (tracing) System.out.println(x+": Only-"+onlyScores[x]+",  temp:"+temp[temp.length-x-1]);
		}	
			
		if (tracing) System.out.println("onlyScores:"+onlyScores[0]+", "+onlyScores[1]+", "+onlyScores[2]+", "+onlyScores[3]+", "+onlyScores[4]+". "); 
		
		
		endHighLabel.setText("<html><center>Your Score: "+score+"<br><br>  Previous Highscores: <br>   "+rawScores[onlyScores.length-1]+ascend[onlyScores[0]%10000].substring(ascend[onlyScores[0]%10000].indexOf('-'), ascend[onlyScores[0]%10000].length())+"<br>   "+rawScores[onlyScores.length-2]+ascend[onlyScores[1]%10000].substring(ascend[onlyScores[1]%10000].indexOf('-'), ascend[onlyScores[1]%10000].length())+"<br>   "+rawScores[onlyScores.length-3]+ascend[onlyScores[2]%10000].substring(ascend[onlyScores[2]%10000].indexOf('-'), ascend[onlyScores[2]%10000].length())+"<br>   "+
			rawScores[onlyScores.length-4]+ascend[onlyScores[3]%10000].substring(ascend[onlyScores[3]%10000].indexOf('-'), ascend[onlyScores[3]%10000].length()) +"<br>   "+rawScores[onlyScores.length-5]+ascend[onlyScores[4]%10000].substring(ascend[onlyScores[4]%10000].indexOf('-'), ascend[onlyScores[4]%10000].length())+"<br><br> Thank You for Playing! <br> Coded By Maggie</html>");
    }
    public int levelColDirect(int lev)
    { 
    	switch(lev)
    	{
    		case 1:
    			return 4; 
    		case 2:
    		case 3: 
    		case 4:
    		case 5:
    			return 5;
    		case 6:
    		case 7:
    		case 8:
    		case 9:
    			return 6;
    		case 10:
    		case 11:
    		case 12:
    		case 13:
    			return 7; 
    		case 14:
    		case 15:
    		case 16:
    		default:
    			return 8; 

    	}
    }
    
    public int levelSymDirect(int lev)
    {
    	switch(lev)
    	{
    		case 1:
    		case 2:
    			return 4;
    		case 3: 
    			return 5;
    		case 4:
    			return 6; 
    		case 5:
    		case 6:
    			return 7; 
    		case 7:
    			return 8;
    		case 8:
    			return 9; 
    		case 9:
    		case 10:
    			return 10; 
    		case 11:
    			return 11;
    		case 12:
    			return 12;
    		case 13:
    		case 14:
    			return 13; 
    		case 15:
    			return 14; 
    		case 16:
    		default:
    			return 15;
    	}
    }
    public Color directColor(char col)
    {
    	Color c=Color.WHITE; 
    	switch (col)
    	{
    		
    		case'A':
    			c=Color.BLUE;
    			break; 
    		case'B':
    			c=new Color(255, 103, 2); //orange
    			break; 
    		case'C':
    			c=Color.WHITE;
    			break; 
    		case'D':
    			c=new Color(23, 125, 54); //green
    			break;  
    		case'E':
    			c=Color.RED;
    			break; 
    		case'F':
    			c=new Color(106, 66, 45); //brown
    			break; 
    		case'G':
    			c=Color.BLACK;
    			break; 
    		case'H':
    			c=new Color(226, 11, 224);
    			break;   
    	}
    	return c; 
    }
//------------------------------------------------------------------------------------------------------------------
    public void mouseClicked(MouseEvent e)
    {
    	if(e.getSource()==start)
    	{
    		String inputValue = ""+JOptionPane.showInputDialog("Please choose a starting level");
    		try
    		{
    			level=Integer.parseInt(inputValue.trim());
    			level=Math.abs(level); 
    			if (level==0)
    			{
    				level=1; 
    			}
    		}
    		catch (Exception t)
    		{
    			level=1; 
    		}
    		
    		
    		gameSetup(); 
    			
    		if (tracing) System.out.println("Level "+level); 
    		mainFrame.setContentPane(playPane); 
    		mainFrame.validate();
    		mainFrame.repaint();
    	}
    	if(e.getSource()==exitGame)
    	{
    		System.exit(0);
    		
    	}
    	if(e.getSource()==goHome)
    	{
    		mainFrame.setContentPane(title); 
    		mainFrame.validate();
    		mainFrame.repaint();
    	}
    	if(e.getSource()==ins)
    	{
    		mainFrame.setContentPane(instructPane); 
    		mainFrame.validate();
    		mainFrame.repaint();
    	}
    	if(e.getSource()==endGame)
    	{
    		String inputName = ""+JOptionPane.showInputDialog("Congratulations, please enter your name: ");
    		inputName=inputName.replaceAll(" ", "_"); 
    		endSequence(inputName.trim()); 
    		mainFrame.setContentPane(endPane); 
    		mainFrame.validate();
    		mainFrame.repaint();
    	}
    	if(e.getSource()==replayGame)
    	{
    		mainFrame.setContentPane(title); 
    		level=1; 
    		score=0; 
    		gameSetup(); 
    		mainFrame.validate();
    		mainFrame.repaint();
    	}
    	if(e.getSource()==discard)
    	{
    		if (dis<4) 
    		{
    			dis++; 
    			progressBar.setValue(dis*25);
				progressBar.setString(dis+" discarded");
				curSym=genSym(); 
		    	curCol=genCol();
		    	
		    	Image charm=toolkit.getImage(curSym+"cur.png");
			    			 
			   	if (curSym==-2)//rock
			   	{
			   		charm=toolkit.getImage("rock.png");  
			    	rune.setOpaque(false);
			    	rune.setIcon(new ImageIcon("rock.png"));  
			   	}
		    	else if (curSym==-3)
		    	{
		    		charm=toolkit.getImage("yellowP.png");  
		    		rune.setOpaque(false); 
		    		rune.setIcon(new ImageIcon("yellowP.png"));  
		    	}
		    	else if (curSym==-4)
		    	{
		    		charm=toolkit.getImage("pinkP.png");    
		    		rune.setOpaque(false);
		    		rune.setIcon(new ImageIcon("pinkP.png")); 
		    	}
		    	else //normal case					
		    	{
		    		charm = toolkit.getImage(curSym+"cur.png"); 
		    		rune.setIcon(new ImageIcon(curSym+".png"));
	    			rune.setOpaque(true);
	    			rune.setBackground(directColor(curCol));  
		    	}
			    												
			   	Cursor use = toolkit.createCustomCursor(charm , hotSpot, "symbol");
			   	playPane.setCursor (use);
    				
    		}
    	}
    	
    	for (int row=1; row<8; row++)
    		for (int col=1; col<8; col++)
    		{
    			if (e.getSource()==gameDisplayArray[row][col])
    			{
    				if (tracing) System.out.println(row+", "+col); 
    					
    				boolean clicked=false; 
    					
    				if (curSym==-2&&has[row][col]==false)//rock
    				{
    					gameDisplayArray[row][col].setIcon(new ImageIcon("rock.png"));
    					gameDisplayArray[row][col].setOpaque(false);
    					
    					if (turn[row][col]==false)
	    				{
	    					end++;  
	    					if (tracing) System.out.println("squares turned: "+end); 
	    				}
	    								
    					turn[row][col]=true; 
	    				has[row][col]=true; 
	    				color[row][col]='r';
	    				symbol[row][col]=-2; 
	    				clicked=true; 
    					
    				}
    				else if (curSym==-3)
    				{
    					clicked=true;
    					if(turn[row][col]==true)
    					{
    						gameDisplayArray[row][col].setIcon(new ImageIcon("blank.png"));
    						gameDisplayArray[row][col].setOpaque(false);
    						has[row][col]=false; 
    						color[row][col]='x';
	    					symbol[row][col]=-1; 
	    					
    					}
    				}
    				else if (curSym==-4)
    				{
    					clicked=true;
    					for(int l=row-1; l<row+2; l++)
    						for (int w=col-1; w<col+2; w++)
    						{
    							if (tracing) System.out.println(l+" ,"+w); 
    							if (turn[l][w]==true)
    							{
    								gameDisplayArray[l][w].setIcon(new ImageIcon("blank.png"));
    								gameDisplayArray[l][w].setOpaque(false);
    								has[l][w]=false; 
    								color[l][w]='x';
	    							symbol[l][w]=-1; 
    							}
    						}
    				}
    				else if (has[row][col]==false)//normal case
    				{
    					boolean valid=false; 
	    				if((color[row-1][col]==curCol)||(symbol[row-1][col]==curSym)||(color[row+1][col]==curCol)||(symbol[row+1][col]==curSym)||
	    					(color[row][col+1]==curCol)||(symbol[row][col+1]==curSym)||(color[row][col-1]==curCol)||(symbol[row][col-1]==curSym))
	    					{
	    						valid=true; 
	    					}
	    				
	    				if (symbol[row-1][col]==-2||symbol[row][col-1]==-2||symbol[row+1][col]==-2||symbol[row][col+1]==-2)
	    				{
	    					valid=true; 
	    				}
	    					
	    				if(valid)
	    				if((color[row-1][col]=='x'||color[row-1][col]==curCol)||(symbol[row-1][col]==-1||symbol[row-1][col]==curSym||symbol[row-1][col]==-2))
	    					if((color[row+1][col]=='x'||color[row+1][col]==curCol)||(symbol[row+1][col]==-1||symbol[row+1][col]==curSym||symbol[row+1][col]==-2))
	    						if((color[row][col+1]=='x'||color[row][col+1]==curCol)||(symbol[row][col+1]==-1||symbol[row][col+1]==curSym||symbol[row][col+1]==-2))
	    							if((color[row][col-1]=='x'||color[row][col-1]==curCol)||(symbol[row][col-1]==-1||symbol[row][col-1]==curSym||symbol[row][col-1]==-2))
	    							{
	    								
	    								gameDisplayArray[row][col].setIcon(new ImageIcon(curSym+".png"));
	    								gameDisplayArray[row][col].setOpaque(true);
	    								gameDisplayArray[row][col].setBackground(directColor(curCol)); 
	    								color[row][col]=curCol; 
	    								symbol[row][col]=curSym;
	    								
	    								if (turn[row][col]==false)
	    								{
	    									end++;  
	    									if (tracing) System.out.println("squares turned: "+end); 
	    								}
	    								
	    								turn[row][col]=true; 
	    								has[row][col]=true; 
	    								clicked=true;
	    								
	    								
	    								if (tracing) System.out.println("new c: "+curCol+"  "+curSym); 
	    							}
    				}
    				if (clicked==true)
    				{
    					if (end>=49)
	    				{
	    					if(tracing) System.out.println("level complete"); 
	    					JOptionPane.showMessageDialog(null, "Level Complete: Proceed to Next Level!", "Level Up", JOptionPane.INFORMATION_MESSAGE);
	    					if (level>=1)
	    					{
	    						score+=200; 
	    					}
	    					if (level>=7)
	    					{
	    						score+=100; 
	    					}
	    					if (level>=12)
	    					{
	    						score+=150; 
	    					}
	    					if (level>=16)
	    					{
	    						score+=150; 
	    					}
	    					if (tracing) System.out.println("score: "+score ); 
	    					scoreLabel.setText("Score: "+score); 
	    					level++; 
	    					gameSetup(); 
	    				}
	    				else
	    				{
	    					if (level>=1)
	    					{
	    						score++; 
	    					}
	    					if (level>=7)
	    					{
	    						score+=2; 
	    					}
	    					if (level>=12)
	    					{
	    						score+=1; 
	    					}
	    					if (level>=16)
	    					{
	    						score+=1; 
	    					}
	    					scoreLabel.setText("Score: "+score); 
	    					if (tracing) System.out.println("score: "+score);  
	    					dis=0; 
	    					progressBar.setValue(dis*25);
							progressBar.setString(dis+" discarded");
							
							boolean rowCheck=true; 
							for (int c=1; c<8; c++)
							{
								if (has[row][c]==false) 
								{	
									rowCheck=false; 
									break; 
								}
							}
							if (rowCheck)
							{
								for (int c=1; c<8; c++)
								{
									gameDisplayArray[row][c].setIcon(new ImageIcon("blank.png"));
		    						gameDisplayArray[row][c].setOpaque(false);
		    						has[row][c]=false; 
		    						color[row][c]='x';
			    					symbol[row][c]=-1; 	
								}
							}
							
							boolean colCheck=true; 
							for (int c=1; c<8; c++)
							{
								if (has[c][col]==false)
								{
									colCheck=false; 
									break; 
								}
							}
							if (colCheck)
							{
								for (int c=1; c<8; c++)
								{
									gameDisplayArray[c][col].setIcon(new ImageIcon("blank.png"));
		    						gameDisplayArray[c][col].setOpaque(false);
		    						has[c][col]=false; 
		    						color[c][col]='x';
			    					symbol[c][col]=-1; 	
								}
							}
				
	    					curCol=genCol();
			    			curSym=genSym(); 
			    			
			    			Image charm=toolkit.getImage(curSym+"cur.png");
			    			 
			    			if (curSym==-2)//rock
			    			{
			    				charm=toolkit.getImage("rock.png");  
			    				rune.setOpaque(false);
			    				rune.setIcon(new ImageIcon("rock.png"));  
			    			}
		    				else if (curSym==-3)
		    				{
		    					charm=toolkit.getImage("yellowP.png");  
		    					rune.setOpaque(false); 
		    					rune.setIcon(new ImageIcon("yellowP.png"));  
		    				}
		    				else if (curSym==-4)
		    				{
		    					charm=toolkit.getImage("pinkP.png");    
		    					rune.setOpaque(false);
		    					rune.setIcon(new ImageIcon("pinkP.png")); 
		    				}
		    				else //normal case					
		    				{
		    					charm = toolkit.getImage(curSym+"cur.png"); 
		    					rune.setIcon(new ImageIcon(curSym+".png"));
	    						rune.setOpaque(true);
	    						rune.setBackground(directColor(curCol));  
		    				}
			    												
			    			Cursor use = toolkit.createCustomCursor(charm , hotSpot, "symbol");
			    			playPane.setCursor (use);
	    				}
    				}
    				
    			}
    		}
    }
	public void mousePressed(MouseEvent e) // use this method to perform actions when the mouse button is pressed
	{

	}

	public void mouseReleased(MouseEvent e) // use this method to perform actions when the mouse button is released
	{
	
	}
	
	
	public void mouseEntered(MouseEvent e)  // use this method to perform actions when the mouse enters a component
	{
		for (int row=1; row<8; row++)
			for (int col=1; col < 8; col++)
			{	
				if (e.getSource()==gameDisplayArray[row][col]&&has[row][col]==false)
				{
				//	if (tracing) System.out.println(color[row][col]+"  "+symbol[row][col]); 
					if (curSym==-2)//rock
    				{
    					gameDisplayArray[row][col].setIcon(new ImageIcon("rock.png"));
    					gameDisplayArray[row][col].setOpaque(false);
    					
    				}
    				else if (curSym==-3||curSym==-4)
    				{
    					int j=1;
    				}
    				else //normal case
    				{	
							boolean valid=false; 
		    				if((color[row-1][col]==curCol)||(symbol[row-1][col]==curSym)||(color[row+1][col]==curCol)||(symbol[row+1][col]==curSym)||
		    					(color[row][col+1]==curCol)||(symbol[row][col+1]==curSym)||(color[row][col-1]==curCol)||(symbol[row][col-1]==curSym))
		    					{
		    						valid=true; 
		    					}
		    				
		    				if (symbol[row-1][col]==-2||symbol[row][col-1]==-2||symbol[row+1][col]==-2||symbol[row][col+1]==-2)
		    				{
		    					valid=true; 
		    				}
		    					

		    				if(valid)
		    				if((color[row-1][col]=='x'||color[row-1][col]==curCol)||(symbol[row-1][col]==-1||symbol[row-1][col]==curSym||symbol[row-1][col]==-2))
		    					if((color[row+1][col]=='x'||color[row+1][col]==curCol)||(symbol[row+1][col]==-1||symbol[row+1][col]==curSym||symbol[row+1][col]==-2))
		    						if((color[row][col+1]=='x'||color[row][col+1]==curCol)||(symbol[row][col+1]==-1||symbol[row][col+1]==curSym||symbol[row][col+1]==-2))
		    							if((color[row][col-1]=='x'||color[row][col-1]==curCol)||(symbol[row][col-1]==-1||symbol[row][col-1]==curSym||symbol[row][col-1]==-2))
		    							{
		
											gameDisplayArray[row][col].setIcon(new ImageIcon(curSym+".png"));
	    									gameDisplayArray[row][col].setOpaque(true);
	    									gameDisplayArray[row][col].setBackground(directColor(curCol)); 
										}
					}
				}
			}	
	}
	
	public void mouseExited(MouseEvent e)  // use this method to perform actions when the mouse exits a component
	{
		for (int row=1; row<8; row++)
			for (int col=1; col <8; col++)
			{	
				if (e.getSource()==gameDisplayArray[row][col]&&turn[row][col]==false)
				{
					gameDisplayArray[row][col].setIcon(unchosenSquare);
				}
				else if (e.getSource()==gameDisplayArray[row][col]&&turn[row][col]==true&&has[row][col]==false)
				{
					gameDisplayArray[row][col].setIcon(new ImageIcon("blank.png"));
					gameDisplayArray[row][col].setOpaque(false);
				}
			}
	
	}
}

