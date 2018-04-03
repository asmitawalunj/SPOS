package process_scheduling;

import java.util.Scanner;

public class scheduling {
static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int ch,ans;
		do
		{
			System.out.println("Enter your choice:= \n1.First come first serve\n2.Round robin\n3.Priority based\n4.SRTF\n\n");
			ch=sc.nextInt();
			
			System.out.println();
			
			switch(ch)
			{
			case 1:fcfs();
					break;
			
					
			case 2:RR();
					break;
					
			case 3:prio();
					break;
					
			case 4:srtf();
					break;
			}
			System.out.println("Do you want to continue??1:Yes 0:No");
			ans=sc.nextInt();
		}while(ans==1);
		
		
	}
	
	public static void fcfs()
	{
		   int wait = 0,x=0;
		   System.out.print("----------------FCFS--------------");
			System.out.println("Enter the number of processes:");
			int n = sc.nextInt();
	        Process[] myProcess = new Process[n];
	        int avgtt,avgwt;
	 		int sumtt=0,sumwt=0;
	        
			for(int i=0;i<n;i++)
			{
				System.out.println("Enter Arrival time and bursts for process "+(i+1)+": ");
				int sub  = sc.nextInt();
				int bur = sc.nextInt();
				myProcess[i] = new Process(sub,bur);
			}
			
	        for(int i=0;i<myProcess.length;i++)
	        {
	            x = x+myProcess[i].bursts;
				myProcess[i].completionTime = x;
				myProcess[i].turnAround = myProcess[i].completionTime - myProcess[i].submission;
				myProcess[i].wait = myProcess[i].turnAround - myProcess[i].bursts;
				System.out.println("Process "+i+":");
				System.out.println("Turnaround\tCompletion\t\t\twaiting");
				System.out.println(myProcess[i].turnAround+"\t\t\t"+myProcess[i].completionTime+"\t\t\t"+myProcess[i].wait);

				 sumtt=sumtt+myProcess[i].turnAround;
				 sumwt=sumwt+myProcess[i].wait;
	        }
	        
	        avgtt=sumtt/myProcess.length;
	        avgwt=sumwt/myProcess.length;
	        System.out.println("Average turnaround time"+avgtt);
	        System.out.println("Average waiting time"+avgwt);
	}

	public static void RR()
	{
		int totalp=0;
		int quantum;
				
		System.out.print("----------------Round Robin---------------"+
		"\n\nEnter no. of process to be executed: ");
		totalp = sc.nextInt();
		
		int burst[] = new int[totalp];
		
		for(int i=0;i<totalp;i++)
		{
			System.out.print("\nEnter CPU burst for process P"+(i+1)+ ": ");
			
			burst[i]=sc.nextInt();
			
		}
		System.out.println("\nEnter the quantum slice : ");
		quantum=sc.nextInt();
		
		//--------------------------------------------------------------------------------
		 int CT=0;
		 int i=0;
		 int pro=totalp;
		 System.out.print("0");
		while(pro!=0)
		{
			if(burst[i] > quantum)
			{
				burst[i]= burst[i]-quantum;
				CT= CT + quantum;
				System.out.print("| P"+ (i+1)+"|" + CT +"|" );
			}
			else if(burst[i] <= quantum  &&  burst[i] >0)
			{
				CT= CT + burst[i];
				burst[i] = burst[i] - burst[i];
				System.out.print("| P"+ (i+1)+"|" + CT +"|" );
				pro--;
			}
			
			i++;
			if(i==totalp)
			{
				i=0;
			}
		}
	}

	public static void prio()
	{
		int p_id;
		
		System.out.print("----------------Priority Based---------------");
		System.out.println("HOW MANY PROCESSES ARE THERE?");
		p_id=sc.nextInt();
		
		int prio[]=new int[p_id];
		int proc[]= {1,2,3,4,5};
		
		int bur[]=new int[p_id];
		int starttime[]=new int[p_id];

	int temp;
	for(int i=0;i<p_id;i++)
	{
	System.out.println("Enter busrt time for " +(i+1));

		bur[i]=sc.nextInt();
		
	}

	System.out.println("Enter the priority sequentially");
	for(int i=0;i<p_id;i++)
	{
		
		prio[i]=sc.nextInt();

	}

	//Bubble sort 
	int l;
		for(int i=0;i<p_id-1;i++)
		{
			for(int j=0;j<p_id-i-1;j++)
			{
				if(prio[j]>prio[j+1])
				{
					temp=prio[j];
					prio[j]=prio[j+1];
					prio[j+1]=temp;
				
					l=proc[j];
					proc[j]=proc[j+1];
					proc[j+1]=l;
			
					temp=bur[j];
					bur[j]=bur[j+1];
					bur[j+1]=temp;
				
				
				}
				
			}
			

		}
	
		
		//logic
		//System.out.print("0");
		for(int i=0;i<p_id;i++)
		{

			if(i==0)
			{
				starttime[0]=0;					
			}
			else 
			{
				starttime[i]=starttime[i-1]+bur[i-1];

			}
			
			System.out.print(starttime[i]+"-----" +"P"+proc[i]+"-----");
			if(i==p_id-1)
			{
				starttime[i]=(starttime[i]+ bur[i]);
			
				System.out.println(starttime[i] );
			}
		}
	
		
	}

	public static void srtf()
	{
		int n;
		
		System.out.print("----------------SRTF---------------");
	      System.out.println("Enter the number of Processes: ");
	       n = sc.nextInt();
	       int proc[][] = new int[n+1][4];//proc[][0] is the AT array,[][1] - BT,[][2] - WT,[][3] - TT
	       for(int i = 1; i <= n; i++)
	       {
	      System.out.println("Enter the Arrival Time for Process " + i + ": ");
	      proc[i][0] = sc.nextInt();
	      System.out.println("Enter the Burst Time for Process " + i + ": ");
	      proc[i][1] =sc.nextInt();
	     }
	       System.out.println();
	     
	       
	     int total_time = 0;
	     for(int i = 1; i <= n; i++)
	     {
	      total_time += proc[i][1];
	     }
	     
	     int time_chart[] = new int[total_time];
	     
	     for(int i = 0; i < total_time; i++)
	     {
	      //Selection of shortest process which has arrived
	      int sel_proc = 0;
	      int min = 99999;
	      for(int j = 1; j <= n; j++)
	      {
	       if(proc[j][0] <= i)//Condition to check if Process has arrived
	       {
	        if(proc[j][1] < min && proc[j][1] != 0)
	        {
	         min = proc[j][1];
	         sel_proc = j;
	        }
	       }
	      }
	      
	     
	      time_chart[i] = sel_proc;
	      
	     
	      proc[sel_proc][1]--;
	      
	      //WT and TT Calculation
	      for(int j = 1; j <= n; j++)
	      {
	       if(proc[j][0] <= i)
	       {
	        if(proc[j][1] != 0)
	        {
	         proc[j][3]++;//If process has arrived and it has not already completed execution its TT is incremented by 1
	            if(j != sel_proc)//If the process has not been currently assigned the CPU and has arrived its WT is incremented by 1
	             proc[j][2]++;
	        }
	        else if(j == sel_proc)//This is a special case in which the process has been assigned CPU and has completed its execution
	         proc[j][3]++;
	       }
	      }
	      
	      
	      if(i != 0)
	      {
	       if(sel_proc != time_chart[i - 1])
	        //If the CPU has been assigned to a different Process we need to print the current value of time and the name of 
	        //the new Process
	       {
	        System.out.print("--" + i + "--P" + sel_proc);
	       }
	      }
	      else
	       System.out.print(i + "--P" + sel_proc);
	      if(i == total_time - 1)//All the process names have been printed now we have to print the time at which execution ends
	       System.out.print("--" + (i + 1));
	      
	     }
	     System.out.println();
	     System.out.println();
	     
	     //Printing the WT and TT for each Process
	     System.out.println("P\t WT \t TT ");
	     for(int i = 1; i <= n; i++)
	     {
	      System.out.printf("%d\t%2dms\t%2dms",i,proc[i][2],proc[i][3]);
	      System.out.println();
	     }
	     
	     System.out.println();
	     
	     //Printing the average WT & TT
	     float WT = 0,TT = 0;
	     for(int i = 1; i <= n; i++)
	     {
	      WT += proc[i][2];
	      TT += proc[i][3];
	     }
	     WT /= n;
	     TT /= n;
	     System.out.println("The Average WT is: " + WT + "ms");
	     System.out.println("The Average TT is: " + TT + "ms");
	}

}


class Process{
    int wait;
    int submission;
    int bursts;
	int turnAround;
	int completionTime = 0;
    Process(int sub,int bur){
        submission = sub;
        bursts = bur;
    }

}