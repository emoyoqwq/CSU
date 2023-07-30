import sys
import csv

def SJFP(list):
    sortedlist = sorted(list, key = lambda x: (int(x[1]), x[2]))
    cur = 0                       #track current location
    chart = []                      #list for gantt chart
    timeChart = []                  #list for time chart
    remainingList = []            #unfinishing process and number of its rest process
    for i in range(len(sortedlist)):        #loop over the sortedlist
        if(cur <= sortedlist[i][1]):        #check if there's executing progress
            if(sortedlist[i][1] - cur > 0):                 #check if cur equals first progress's arrival time
                chart.append([cur, "IDLE", sortedlist[i][1]])  
            cur = sortedlist[i][1]          #update cur
            timeChart.append([sortedlist[i][0], cur - sortedlist[i][1], 0])         #update timeChart with waiting time
            chart.append([cur, sortedlist[i][0], cur + sortedlist[i][2]])           #update gantt chart
            cur += sortedlist[i][2]         #update cur
        else:                                                        
            if(cur - sortedlist[i][1] <= sortedlist[i][2]):                         #compare the remaining progress of executing process with the burst time of next process 
                chart.append([cur, sortedlist[i][0], cur + sortedlist[i][2]])       #update gantt chart
                timeChart.append([sortedlist[i][0], cur - sortedlist[i][1], 0])     #update timeChart with waiting time.
                cur += sortedlist[i][2]
            else:                 
                for j in range(len(chart)):                             #this loop is to delete the progress shouldn't be done in gantt chart
                    if(chart[j][2] > sortedlist[i][1]):                 #if current process arival time is less than latest cur in gantt chart, then delete all the rows in gantt with larger cur
                        remainingList.append([chart[j][1], chart[j][2] - sortedlist[i][1]])
                        cur = chart[j][0]
                        chart.pop(j)
                if(cur != sortedlist[i][1]):                            #avoid that current process arrival time equals cur.
                    chart.append([cur, sortedlist[i-1][0], sortedlist[i][1]])               
                    cur = sortedlist[i][1]
                chart.append([cur, sortedlist[i][0], cur + sortedlist[i][2]])   #because the progress of current process is less than last process, so add it in chart
                timeChart.append([sortedlist[i][0], cur - sortedlist[i][1], 0]) #update timeChart with current process's waiting time
                for m in range(len(remainingList)):                     #update process's waiting time which is in remaining list
                    for n in range(len(timeChart)):
                        if(remainingList[m][0] == timeChart[n][0]):
                            timeChart[n][1] += sortedlist[i][2]
                cur += sortedlist[i][2]                                 #update cur
                remainingList = sorted(remainingList, key = lambda x: (int(x[1]), x[0]))        #sort remainglist according to their rest progress
                while len(remainingList) > 0:                                                   #execute process in remaining list into chart, and it will be checked at the top of loop
                    chart.append([cur, remainingList[0][0], cur + remainingList[0][1]])         #if they should be execute later, they will be deleted in gantt chart at the top of loop
                    cur += remainingList[0][1]                          #update cur
                    remainingList.pop(0)                                #delete remainging list when they are executed and added in to gantt chart
    timeChart.sort()        #sort timeChart
    sortedlist.sort()       #sort sortedlist which contains information of processes
    for i in range(len(sortedlist)):                #do a loop to update timeChart
        for j in range(len(chart)):
            if(chart[j][1] == sortedlist[i][0]):    #cuz sortedlist and timeChart have same length and both are sorted, so I just calculate turnaround time according process id
                timeChart[i][2] = chart[j][2] - sortedlist[i][1]
    #print chart
    print("----------------- SJFP -----------------")
    print("Process ID | Waiting Time | Turnaround Time")
    for i in range(len(timeChart)):
        print("    {}      |      {}       |        {}       ".format(timeChart[i][0], timeChart[i][1], timeChart[i][2]))
    print("\nGantt Chart is:")
    for i in range(len(chart)):
        print("[  {}   ]--  {}   --[  {}   ]".format(str(chart[i][0]).center(5),str(chart[i][1]).center(5),str(chart[i][2]).center(5)))

    #calculate info that is needed in output
    avgWaitingTime = 0
    avgTurnTime = 0
    sumWaitingTime = 0
    sumTurnTime = 0
    for i in timeChart:
        sumWaitingTime += i[1]
    for i in timeChart:
        sumTurnTime += i[2]
    throughput = len(timeChart) / chart[-1][2]
    avgWaitingTime = sumWaitingTime / len(timeChart)
    avgTurnTime = sumTurnTime / len(timeChart)
    
    print("\nAverage Waiting Time:  {}".format(avgWaitingTime))
    print("Average Turnaround Time:  {}".format(avgTurnTime))
    print("Throughput:  {}".format(throughput))
                
def FCFS(list):
    sortedlist = sorted(list, key = lambda x: (int(x[1]), x[0]))            #sort process information according to theirs arrival time
    cur = 0                 #current location
    chart = []              #list for gantt chart
    timeChart = []          #list for time information
    for i in range(len(sortedlist)):    #loop over the process list
        waiting = 0         #waiting time
        turnaround = 0      #turnaround time
        if(cur < sortedlist[i][1]):                 #check if there is any process arrives
            chart.append([cur, "IDLE", sortedlist[i][1]])
            cur = sortedlist[i][1]                 #update cur
            turnaround = waiting + sortedlist[i][2]         #update turnaround time.
            timeChart.append([sortedlist[i][0], waiting, turnaround])       #update timeChart
            chart.append([cur, sortedlist[i][0], cur + sortedlist[i][2]])
            cur += sortedlist[i][2]                 #update cur
        else:                                       #if current process's arrival time is less than cur, just add it to gantt chart and calculate its waiting time and turnaround time
            chart.append([cur, sortedlist[i][0], cur + sortedlist[i][2]])
            waiting = cur - sortedlist[i][1]
            turnaround = waiting + sortedlist[i][2]
            timeChart.append([sortedlist[i][0], waiting, turnaround])
            cur += sortedlist[i][2]
    timeChart.sort()
    #print
    print("----------------- FCFS -----------------")
    print("Process ID | Waiting Time | Turnaround Time")
    for i in range(len(timeChart)):
        print("    {}      |      {}       |        {}       ".format(timeChart[i][0], timeChart[i][1], timeChart[i][2]))
    print("\nGantt Chart is:")
    for i in range(len(chart)):
        print("[  {}   ]--  {}   --[  {}   ]".format(str(chart[i][0]).center(5),str(chart[i][1]).center(5),str(chart[i][2]).center(5)))
    #calculated needed output
    avgWaitingTime = 0
    avgTurnTime = 0
    sumWaitingTime = 0
    sumTurnTime = 0
    for i in timeChart:
        sumWaitingTime += i[1]
    for i in timeChart:
        sumTurnTime += i[2]
    throughput = len(timeChart) / chart[-1][2]
    avgWaitingTime = sumWaitingTime / len(timeChart)
    avgTurnTime = sumTurnTime / len(timeChart)
    
    print("\nAverage Waiting Time:  {}".format(avgWaitingTime))
    print("Average Turnaround Time:  {}".format(avgTurnTime))
    print("Throughput:  {}".format(throughput))

if __name__ == "__main__":
    csvFile = open("processes.csv", "r")
    reader = csv.reader(csvFile)
    result = []
    for item in reader:
        if reader.line_num == 1:
            continue
        result.append(item)
    for i in range(len(result)):
        for j in range(len(result[i])):
            result[i][j] = int(result[i][j])
    csvFile.close()
    result.sort()
    FCFS(result)
    SJFP(result)
    
    