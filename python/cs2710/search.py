from __future__ import nested_scopes
import string
from utils import *
from copy import *
""" usage:  python search.py all 3  to run all the search algorithms
                on problem3

            python search.py none 3 to use problem 3 when a particular call is hardcoded

            python search.py <anything> 2 to run a few search algorithms
                on problem2

"""
#
# Note:  to use the global variables X1 and X2, initialize X1 and X2 before you call any functions accessing it,
#        and then include this line in any function that changes the values of X1 and X2:
#        global X1,X2
#
#==========================


class Node:

    def __init__(self, state, statename="", parent=None, depth=0, gval=0, hval=None):
        self.state = state
        if statename:
            self.statename = statename

            else:
                self.statename = state
            self.parent = parent
            self.depth = depth
            self.gval = gval

      self.hval = hval

   def printNode(self):
      print "State: ", self.statename, " Gval=",self.gval,
      print "Hval=",self.hval,"depth=",self.depth,
      if self.parent:
         print " Parent state:", self.parent.statename
      else:
         print "root node"

   def __str__(self):
      strings = ["(depth:", ", gval:",", hval:",", fval:"]
      nums =    [str(self.depth),str(self.gval),\
                 str(h(self)),str(self.gval + h(self))]
      s = " "
      for i in range(4):
         s = s + strings[i]
         if int(nums[i]) < 10:   s = s + " "
         if int(nums[i]) < 100:  s = s + " "
         s = s + nums[i]
      s = s + ")"
      return self.statename + s

def makeNodes (n, succStates):
   return [Node(s,parent=n,depth=1+n.depth,\
            gval=n.gval+edgecost(n.state,s))\
            for s in succStates]

problemPrintString6 = "Problem 6.  Romania example in text."

startState6 = "arad"

succDict6 = {'buch': ['pit', 'faga', 'urz', 'giu'], 'cra': ['dob', 'rim', 'pit'], 'hir': ['efo', 'urz'], 'tim': ['arad', 'lug'], 'dob': ['cra', 'meh'], 'giu': ['buch'], 'efo': ['hir'], 'meh': ['dob', 'lug'], 'nea': ['ias'], 'zer': ['arad', 'ora'], 'sib': ['arad', 'ora', 'faga', 'rim'], 'rim': ['sib', 'pit', 'cra'], 'ias': ['nea', 'vas'], 'arad': ['zer', 'sib', 'tim'], 'lug': ['meh', 'tim'], 'vas': ['urz', 'ias'], 'faga': ['sib', 'buch'], 'pit': ['rim', 'cra', 'buch'], 'urz': ['buch', 'hir', 'vas'], 'ora': ['zer', 'sib']}

edgeDict6 = {('buch', 'pit'): 101, ('cra', 'rim'): 146, ('hir', 'urz'): 98, ('arad', 'tim'): 118, ('sib', 'faga'): 99, ('rim', 'sib'): 80, ('cra', 'pit'): 138, ('lug', 'meh'): 70, ('sib', 'ora'): 151, ('meh', 'lug'): 70, ('rim', 'pit'): 97, ('efo', 'hir'): 86, ('vas', 'urz'): 142, ('tim', 'arad'): 118, ('urz', 'buch'): 85, ('arad', 'zer'): 75, ('nea', 'ias'): 87, ('pit', 'cra'): 138, ('ias', 'nea'): 87, ('zer', 'ora'): 71, ('faga', 'sib'): 99, ('arad', 'sib'): 140, ('ias', 'vas'): 92, ('sib', 'arad'): 140, ('pit', 'buch'): 101, ('rim', 'cra'): 146, ('meh', 'dob'): 75, ('hir', 'efo'): 86, ('pit', 'rim'): 97, ('dob', 'meh'): 75, ('ora', 'sib'): 151, ('sib', 'rim'): 80, ('vas', 'ias'): 92, ('buch', 'urz'): 85, ('buch', 'faga'): 211, ('faga', 'buch'): 211, ('giu', 'buch'): 90, ('zer', 'arad'): 75, ('urz', 'hir'): 98, ('lug', 'tim'): 111, ('dob', 'cra'): 120, ('buch', 'giu'): 90, ('ora', 'zer'): 71, ('urz', 'vas'): 142, ('cra', 'dob'): 120, ('tim', 'lug'): 111}

hDict6 = {'buch': 0, 'sib': 253, 'dob': 242, 'giu': 77, 'lug': 244, 'rim': 193, 'hir': 151, 'nea': 234, 'zer': 374, 'tim': 329, 'meh': 241, 'cra': 160, 'urz': 80, 'arad': 366, 'efo': 161, 'vas': 199, 'ora': 380, 'pit': 100, 'ias': 226, 'faga': 176}

goalDict6 = {'buch': 1}



def printPathToNode(node):
    if node:
        node.printNode()
        if node.parent:
            printPathToNode(node.parent)


def printGraph(root):
    """ Print the graph defined by the dictionaries defining the empty problem.
        Cycles are handled, but the printed graph in that case may not be intuitive.
        I suggest you draw the graph defined by succDict on paper and refer to your drawing
        to understand the output of the algorithms"""
    printed = {root: 1}
    print root
    printGraphHelp(printed, root, succDict[root], '  ')

def printGraphHelp(printed,parent,kids,space):
    for kid in kids:
        if printed.has_key(kid):
            print space,parent,"-->",kid,"edgeCost=",edgeDict[(parent,kid)],"**Edge to state already printed**"
        else:
            printed[kid] = 1
            print space + parent + "-->" + kid + " edgeCost=" + str(edgeDict[(parent,kid)]) + " h(" + kid + ")=" + str(hDict[kid])
            printGraphHelp(printed,kid,succDict[kid],space + '   ')

def printFringe(q):
    print "FRINGE:"
    if isinstance(q, list):
  f = []
  for i in range(0,len(q)):
           x = q.pop()
           print x
           f.append(x)
      for i in range(0,len(f)):
           x = f.pop()
           q.append(x)
    else:
  f = FIFOQueue()
      for i in range(0,len(q)):
           x = q.pop()
           print x
           f.append(x)
      for i in range(0,len(f)):
           x = f.pop()
           q.append(x)


#====== Functions defining the problem. 
def h(node):
    if not node.hval:
        node.hval = hDict[node.state]
    return node.hval


def successors(node):
    return succDict[node.state]

def goalp(node):
    return goalDict.has_key(node.state)

def edgecost (state1, state2):
    return edgeDict[(state1,state2)]

#======= The core search functions and auxiliaries

def treesearch(start, fringe):
    """Search through the successors of a problem to find a goal.

    start -- the start state
    fringe -- an empty queue
    Doesn't worry about repeated paths to a state """
    fringe.append(Node(start))
    while len(fringe) > 0:
        current = fringe.pop()
        if goalp(current):
            return current
        if current.depth >= INFINITY:
            print "** REACHED INFINITY; giving up"
            return []
        if printVerbose:
            print "CURRENT:",
            print(current)
        fringe.extend(makeNodes(current, successors(current)))
        if printVerbose:
            printFringe(fringe)

    return []


def graphsearch(start,fringe):
    """Search through the successors of a problem to find a goal.

    start -- the start state
    fringe -- an empty queue
    If a new path to a state is found, save the shortest-length path"""

    expanded = {}
    fringe.append(Node(start))
    while len(fringe) > 0:
        current = fringe.pop()
        if current.depth >= INFINITY:
            print "** REACHED INFINITY; giving up"
            return []
        # The next if statement is just for printing
        if printVerbose and expanded.has_key(current.state):
            if expanded[current.state].gval <= current.gval:
                print "**New path to",current.statename,"from",current.parent.statename,\
                "is NOT better than the old path to",current.statename
            else:
                print "**New path to",current.statename,"from",current.parent.statename,\
                "IS better than the old path to",current.statename
        if not (expanded.has_key(current.state) and\
                expanded[current.state].gval <= current.gval):
            expanded[current.state] = current
            if goalp(current):
                return current
            if printVerbose:
                print "VISITED:",
                print(current)
            fringe.extend(makeNodes(current,successors(current)))
            if printVerbose:
                printFringe(fringe)
    return []

def depthfirst(start, coreAlgorithm=treesearch):
    """Search the deepest nodes in the search tree first. """
    return coreAlgorithm(start, Stack())


def breadthfirst(start, coreAlgorithm=treesearch):
    """Search the shallowest nodes in the search tree first. """
    return coreAlgorithm(start, FIFOQueue())


def uniformcost(start, coreAlgorithm=treesearch):
    """Search the nodes with the lowest path cost first. """
    return coreAlgorithm(start,
                         PriorityQueue(lambda a, b: a.gval < b.gval))


def bestfirst(start, coreAlgorithm=treesearch):
    """Search the nodes with the lowest h scores first.
       Called greedy search in Russell and Norvig 1st edition,
       and greedy best-first search in the 2nd edition.  """
    return coreAlgorithm(start,
                         PriorityQueue(lambda a, b: h(a) < h(b)))


def Astar(start, coreAlgorithm=treesearch):
    """Search the nodes with the lowest f=h+g scores first."""
    return coreAlgorithm(start,
                         PriorityQueue(lambda a, b: h(a) + a.gval < h(b) + b.gval))


def iterativeDeepening(start):
    result = []
    depthlim = 1
    startnode = Node(start)
    while not result and depthlim < INFINITY:
        result = depthLimSearch([startnode], depthlim)
        depthlim = depthlim + 1
        if depthlim == INFINITY:
            print "** REACHED INFINITY; giving up"
    return result

def depthLimSearch(fringe, depthlim):
    if printVerbose:
        print ""
        print "**Starting at root with depthLim=%d" % (depthlim)
        print ""
    while fringe:
        current = fringe[0]
        fringe = fringe[1:]
        if goalp(current):
            return current
        if printVerbose:
            print "VISITED:",
            print(current)
        if current.depth <= depthlim:
            fringe = makeNodes(current, successors(current)) + fringe
        if printVerbose:
            printFringe(fringe)
    return []


def IDAstar(start):
    result = []
    startNode = Node(start)
    fLim = h(startNode)
    while not result and fLim < FINFINITY:
        if printVerbose:
            print ""
            print "**Starting at root with fLim=%d" % (fLim)
            print ""
        result, fLim = fLimSearch([startNode], fLim)
        if fLim == FINFINITY:
            print "** REACHED INFINITY; giving up"
    return result



def fLimSearch(fringe, fLim):
    nextF = FINFINITY
    while fringe:
        current = fringe[0]
        fringe = fringe[1:]
        currentF = current.gval + h(current)
        if currentF <= fLim:
            if goalp(current):
                return (current, currentF)
            if printVerbose:
                print "VISITED:",
                print(current)
            succNodes = makeNodes(current, successors(current))
            for s in succNodes:
                fVal = s.gval + h(s)
                if fVal > fLim and fVal < nextF:
                    nextF = fVal
            fringe = succNodes + fringe
    return ([], nextF)


def beamsearch(start, beamwidth):

    def insertByH(item, lst):
        i = 0
        while i < len(lst) and h(lst[i]) < h(item):
            i = i + 1
        return lst[:i] + [item] + lst[i:]

    fringe = [Node(start)]
    while len(fringe) > 0:
        current = fringe[0]
        fringe = fringe[1:]
        if goalp(current):
            return current
        if current.depth >= INFINITY:
            print "** REACHED INFINITY; giving up"
            return []
        if printVerbose:
            print "LEN(FRINGE):", len(fringe), "VISITED:",
            print(current)
        newnodes = makeNodes(current, successors(current))
        for s in newnodes:
            fringe = insertByH(s, fringe)
        fringe = fringe[:beamwidth]
    return []


#==== Main program.  Change this as appropriate for your application.
#==== usage:  "python search.py all 3" to run all the search algorithms on problem 3
#====         "python search.py some 3 " to run a few search algorithms on problem 3

print sys.argv
printVerbose = 1
INFINITY = 30   # depth infinity
FINFINITY = 5000  # f-limit infinity
# numRunsEachAlg = 100

# Set the problem to Problem sys.argv[2].
# exec(string) executes the command represented by the string.
# For example, exec("x = 5") sets variable x to 5.
exec("problemPrintString = problemPrintString" + sys.argv[2])
exec("startState = startState" + sys.argv[2])
exec("succDict = succDict" + sys.argv[2])
exec("edgeDict = edgeDict" + sys.argv[2])
exec("hDict = hDict" + sys.argv[2])
exec("goalDict = goalDict" + sys.argv[2])

print "==== Start State: ===="
print problemPrintString
printGraph(startState)
print ""
print "==== Calls to Search Algorithms ===="
print ""
if sys.argv[1] == "all":
    for strat in [breadthfirst, uniformcost, depthfirst, bestfirst, Astar]:
        for corealg in [treesearch, graphsearch]:
            print "======================================================"
            print "strategy:", strat.__name__
            print "core algorithm:", corealg.__name__
            result = strat(startState, corealg)
            if result:
                print "====Following is the (reverse) Path to the Goal===>"
                printPathToNode(result)
            else:
                print "No goal found"
    print "======================================================"
    print "strategy: iterativeDeepening"
    result = iterativeDeepening(startState)
    if result:
        print "====Following is the (reverse) Path to the Goal===>"
        printPathToNode(result)
    else:
        print "No goal found"
    print "======================================================"
    print "strategy: IDAstar"
    result = IDAstar(startState)
    if result:
        print "====Following is the (reverse) Path to the Goal===>"
        printPathToNode(result)
    else:
        print "No goal found"
    print "======================================================"
    print "strategy: Beamsearch, with a beam-width of 5"
    result = beamsearch(startState, 5)
    if result:
        print "====Following is the (reverse) Path to the Goal===>"
        printPathToNode(result)
    else:
        print "No goal found"
elif (sys.argv[1] == "some"):
    for strat in [bestfirst, Astar]:
        for corealg in [treesearch, graphsearch]:
            print "======================================================"
            print "strategy:", strat.__name__
            print "core algorithm:", corealg.__name__
            result = strat(startState, corealg)
            if result:
                print "====Following is the (reverse) Path to the Goal===>"
                printPathToNode(result)
            else:
                print "No goal found"
    print "======================================================"
    print "strategy: IDAstar"
    result = IDAstar(startState)
    if result:
        print "====Following is the (reverse) Path to the Goal===>"
        printPathToNode(result)
    else:
        print "No goal found"
else:
    print "======================================================"
    print "strategy: iterativeDeepening"
    result = iterativeDeepening(startState)
    if result:
        print "====Following is the (reverse) Path to the Goal===>"
        printPathToNode(result)
    else:
        print "No goal found"
