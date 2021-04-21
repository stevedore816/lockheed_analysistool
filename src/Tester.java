import java.util.ArrayList;

import SQLDatabase.loginHandler;
import parseTreeAttempt.Classes;
import parseTreeAttempt.Loop;
import parseTreeAttempt.Method;
import parseTreeAttempt.ifStatement;

public class Tester {
	
	
	public static void demonstrate() {
		String python = "# searchAgents.py\r\n" + 
				"\r\n" + 
				"class StayEastSearchAgent(SearchAgent):\r\n" + 
				"    \"\"\"\r\n" + 
				"    An agent for position search with a cost function that penalizes being in\r\n" + 
				"    positions on the West side of the board.\r\n" + 
				"\r\n" + 
				"    The cost function for stepping into a position (x,y) is 1/2^x.\r\n" + 
				"    \"\"\"\r\n" + 
				"    def __init__(self):\r\n" + 
				"        self.searchFunction = search.lowestCostFirstSearch\r\n" + 
				"        costFn = lambda pos: .5 ** pos[0]\r\n" + 
				"        self.searchType = lambda state: PositionSearchProblem(state, costFn, (1, 1), None, False)\r\n" + 
				"\r\n" + 
				"class StayWestSearchAgent(SearchAgent):\r\n" + 
				"    \"\"\"\r\n" + 
				"    An agent for position search with a cost function that penalizes being in\r\n" + 
				"    positions on the East side of the board.\r\n" + 
				"\r\n" + 
				"    The cost function for stepping into a position (x,y) is 2^x.\r\n" + 
				"    \"\"\"\r\n" + 
				"    def __init__(self):\r\n" + 
				"        self.searchFunction = search.lowestCostFirstSearch\r\n" + 
				"        costFn = lambda pos: 2 ** pos[0]\r\n" + 
				"        self.searchType = lambda state: PositionSearchProblem(state, costFn)\r\n" + 
				"\r\n" + 
				"def manhattanHeuristic(position, problem, info={}):\r\n" + 
				"    \"The Manhattan distance heuristic for a PositionSearchProblem\"\r\n" + 
				"    xy1 = position\r\n" + 
				"    xy2 = problem.goal\r\n" + 
				"    return abs(xy1[0] - xy2[0]) + abs(xy1[1] - xy2[1])\r\n" + 
				"\r\n" + 
				"def euclideanHeuristic(position, problem, info={}):\r\n" + 
				"    \"The Euclidean distance heuristic for a PositionSearchProblem\"\r\n" + 
				"    xy1 = position\r\n" + 
				"    xy2 = problem.goal\r\n" + 
				"    return ( (xy1[0] - xy2[0]) ** 2 + (xy1[1] - xy2[1]) ** 2 ) ** 0.5\r\n" + 
				"\r\n" + 
				"#####################################################\r\n" + 
				"# This portion is incomplete.  Time to write code!  #\r\n" + 
				"#####################################################\r\n" + 
				"\r\n" + 
				"class CornersProblem(search.SearchProblem):\r\n" + 
				"    \"\"\"\r\n" + 
				"    This search problem finds paths through all four corners of a layout.\r\n" + 
				"\r\n" + 
				"    You must select a suitable state space and successor function\r\n" + 
				"    \"\"\"\r\n" + 
				"\r\n" + 
				"    def __init__(self, startingGameState):\r\n" + 
				"        \"\"\"\r\n" + 
				"        Stores the walls, pacman's starting position and corners.\r\n" + 
				"        \"\"\"\r\n" + 
				"        \r\n" + 
				"        self.walls = startingGameState.getWalls()\r\n" + 
				"        self.startingPosition = startingGameState.getPacmanPosition()\r\n" + 
				"        top, right = self.walls.height-2, self.walls.width-2\r\n" + 
				"        self.corners = ((1,1), (1,top), (right, 1), (right, top))\r\n" + 
				"        for corner in self.corners:\r\n" + 
				"            if not startingGameState.hasFood(*corner):\r\n" + 
				"                print('Warning: no food in corner ' + str(corner))\r\n" + 
				"        self._expanded = 0 # DO NOT CHANGE; Number of search nodes expanded\r\n" + 
				"        # Please add any code here which you would like to use\r\n" + 
				"        # in initializing the problem\r\n" + 
				"        \"*** YOUR CODE HERE ***\"\r\n" + 
				"        \"The idea is if the pos is a corner set one of the positions to false so theres 3 corners remaining\"\r\n" + 
				"        self.cornersRemaining = [True, True, True,True]\r\n" + 
				"        #By doing this im virtually creating a value that checks for these two object with my custom start state\r\n" + 
				"        self.startState = (self.startingPosition, self.cornersRemaining)\r\n" + 
				"    def getStartState(self):\r\n" + 
				"        \"\"\"\r\n" + 
				"        Returns the start state (in your state space, not the full Pacman state\r\n" + 
				"        space)\r\n" + 
				"        \"\"\"\r\n" + 
				"        \"*** YOUR CODE HERE ***\"\r\n" + 
				"        return self.startState\r\n" + 
				"\r\n" + 
				"    def isGoalState(self, state):\r\n" + 
				"        \"\"\"\r\n" + 
				"        Returns whether this search state is a goal state of the problem.\r\n" + 
				"        \"\"\"\r\n" + 
				"        \"*** YOUR CODE HERE ***\"\r\n" + 
				"        position,cornersRemaining = state\r\n" + 
				"       # print (cornersRemaining)\r\n" + 
				"        if cornersRemaining == [False, False, False, False]:\r\n" + 
				"            print(position)\r\n" + 
				"            return True\r\n" + 
				"    def getSuccessors(self, state):\r\n" + 
				"        \"\"\"\r\n" + 
				"        Returns successor states, the actions they require, and a cost of 1.\r\n" + 
				"\r\n" + 
				"         As noted in search.py:\r\n" + 
				"            For a given state, this should return a list of triples, (successor,\r\n" + 
				"            action, stepCost), where 'successor' is a successor to the current\r\n" + 
				"            state, 'action' is the action required to get there, and 'stepCost'\r\n" + 
				"            is the incremental cost of expanding to that successor\r\n" + 
				"        \"\"\"\r\n" + 
				"\r\n" + 
				"        successors = []\r\n" + 
				"        for action in [Directions.NORTH, Directions.SOUTH, Directions.EAST, Directions.WEST]:\r\n" + 
				"            # Add a successor state to the successor list if the action is legal\r\n" + 
				"            # Here's a code snippet for figuring out whether a new position hits a wall:\r\n" + 
				"            #   x,y = currentPosition\r\n" + 
				"            #   dx, dy = Actions.directionToVector(action)\r\n" + 
				"            #   nextx, nexty = int(x + dx), int(y + dy)\r\n" + 
				"            #   hitsWall = self.walls[nextx][nexty]\r\n" + 
				"\r\n" + 
				"            \"*** YOUR CODE HERE ***\"\r\n" + 
				"            currentPos,cornersRemaining = state\r\n" + 
				"            x,y = currentPos\r\n" + 
				"            dx, dy = Actions.directionToVector(action)\r\n" + 
				"            nextx, nexty = int(x + dx), int(y + dy)\r\n" + 
				"            nextRemaining = cornersRemaining[:] #deep copy of the array which seriously messed me up\r\n" + 
				"            if not self.walls[nextx][nexty]:\r\n" + 
				"                valCounter = 0 \r\n" + 
				"                \"This for loop essentially checks all the corners and if atleast one corner is in the next value, it breaks\"\r\n" + 
				"                for corner in self.corners: \r\n" + 
				"                    if ((nextx, nexty) == corner):\r\n" + 
				"                        break\r\n" + 
				"                    valCounter+=1\r\n" + 
				"                \"This statement essentially means if atleast one of the corners are explored 0..3\"\r\n" + 
				"                if valCounter < 4:\r\n" + 
				"                    nextRemaining[valCounter] = False; \r\n" + 
				"                newState = ((nextx,nexty),nextRemaining)\r\n" + 
				"                successors.append(( newState, action, 1) )       \r\n" + 
				"        self._expanded += 1 # DO NOT CHANGE\r\n" + 
				"        return successors\r\n" + 
				"\r\n" + 
				"    def getCostOfActions(self, actions):\r\n" + 
				"        \"\"\"\r\n" + 
				"        Returns the cost of a particular sequence of actions.  If those actions\r\n" + 
				"        include an illegal move, return 999999.  This is implemented for you.\r\n" + 
				"        \"\"\"\r\n" + 
				"        if actions == None: return 999999\r\n" + 
				"        x,y= self.startingPosition\r\n" + 
				"        for action in actions:\r\n" + 
				"            dx, dy = Actions.directionToVector(action)\r\n" + 
				"            x, y = int(x + dx), int(y + dy)\r\n" + 
				"            if self.walls[x][y]: return 999999\r\n" + 
				"        return len(actions)\r\n";
		String code = "package parseTreeAttempt;\r\n" + 
				"\r\n" + 
				"import java.util.ArrayList;\r\n" + 
				"\r\n" + 
				"public class Method extends Classes{\r\n" + 
				"	public String string; \r\n" + 
				"	public Method(String string) {\r\n" + 
				"		super(string);\r\n" + 
				"		this.string = string; \r\n" + 
				"	}\r\n" + 
				"	public String toString() {\r\n" + 
				"		return string; \r\n" + 
				"	}\r\n" + 
				"	public boolean hasIfs() {\r\n" + 
				"		return findIfs().size() > 0; \r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"	public boolean hasLoops() {\r\n" + 
				"		return findLoops().size() > 0; \r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"	public ArrayList<ifStatement> findIfs() {\r\n" + 
				"		ArrayList<String> searches = super.searchCode(string, \"if[ |(].+[) ][{|:]\");\r\n" + 
				"		ArrayList<ifStatement> ifs = new ArrayList<ifStatement>();\r\n" + 
				"		for (int i = 1; i < searches.size(); i++) {\r\n" + 
				"			String search = searches.get(i - 1); \r\n" + 
				"			int startPos = string.indexOf(search) + search.length();\r\n" + 
				"			int endPos = string.indexOf(searches.get(i)); \r\n" + 
				"			String sub = string.substring(startPos, endPos); \r\n" + 
				"			ifStatement statement = new ifStatement(sub); \r\n" + 
				"			if (!statement.hasIfs()) {\r\n" + 
				"				ifs.add(statement); \r\n" + 
				"			} else {\r\n" + 
				"				int count = 0;\r\n" + 
				"				ifStatement tempStatement = statement; \r\n" + 
				"				while(tempStatement.hasIfs()) {\r\n" + 
				"					count++; \r\n" + 
				"					tempStatement = tempStatement.findIfs().get(0);\r\n" + 
				"				}\r\n" + 
				"			}\r\n" + 
				"		}\r\n" + 
				"		return ifs; \r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"	public ArrayList<Loop> findLoops() {\r\n" + 
				"		ArrayList<String> search = super.searchCode(string, \"(for|while|do)[ |(].+[{|:]\"); \r\n" + 
				"		ArrayList<Loop> loops = new ArrayList<Loop>();\r\n" + 
				"		for (int i = 1; i < search.size(); i++) {\r\n" + 
				"			int startPos = code.indexOf(search.get(i - 1));\r\n" + 
				"			int endPos = code.indexOf(search.get(i)); \r\n" + 
				"			String sub = code.substring(startPos, endPos); \r\n" + 
				"			loops.add(new Loop(sub)); \r\n" + 
				"		}\r\n" + 
				"		return loops; \r\n" + 
				"	}\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"";
		Classes clases = new Classes(code) ;
		Classes claspy = new Classes(python) ;
		ArrayList<Method> method = clases.getMethods();
		String print = ""; 
			System.out.print("Methods: " + method);
			System.out.print("\nLoops: " + method.get(method.size() - 1).findLoops());
			System.out.println("\nIfs: " + method.get(method.size() - 1).findLoops().get(1).findIfs());
			
			
			ArrayList<Method> methods = claspy.getMethods();
			System.out.print("Methods: " + methods);
			System.out.print("\nLoops: " + methods.get(methods.size() - 1).findLoops());
			System.out.println("\nIfs: " + ( methods.get(methods.size() - 1).findLoops().get(1).findIfs().get(0).findIfs())); 
		
	}
	
	public static void main (String[] args) {
		demonstrate();
	}
	
	
}
