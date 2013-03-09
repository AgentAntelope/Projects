from __future__ import nested_scopes
import string
from utils import *
import copy
""" Partial-Order Planning
    usage:  python plan.py 1  to run, e.g.,  planning problem 1 hardcoded in this file.
"""
#==========================


class Node:

    def __init__(self, state, statename=[], parent=[], depth=0, gval=0, hval=None):
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
        print "State: ", self.statename, " Gval=", self.gval,
        print "Hval=", self.hval, "depth=", self.depth,
        if self.parent:
            print " Parent state:", self.parent.statename
        else:
            print "root node"

    def inlineprintNode(self):
        strings = ["(depth:", ", gval:", ", hval:", ", fval:"]
        nums = [str(self.depth), str(self.gval),
                str(h(self)), str(self.gval + h(self))]
        s = ""
        for i in range(4):
            s = s + strings[i]
            if int(nums[i]) < 10:
                s = s + " "
            if int(nums[i]) < 100:
                s = s + " "
            s = s + nums[i]
        s = s + ")"
        print self.statename, s


def makeNodes(n, succStates):
    return [Node(s, parent=n, depth=1 + n.depth,
                 gval=n.gval + edgecost(n.state, s))
                for s in succStates]

#=====Utils (to help create propositionalized versions of FOL actions)


def cr(l, e):
    r = copy.copy(l)
    r.remove(e)
    return r


def perm(l):
    if len(l) == 0:
        return [[]]
    else:
        res = [([h] + t) for h in l for t in perm(cr(l, h))]
        return res


def astr(name, vals):
    s = name + "("
    if vals:
        s = s + str(vals[0])
        for r in vals[1:]:
            s = s + "," + str(r)
    s = s + ")"
    return s

# =======================
# States are plans


class Plan:

    def __init__(self, steps=[], ordercons=[], causallinks=[],
                 openconds=[], threats=[]):
        global gensymplan
        self.steps = steps  # [step]
        self.ordercons = ordercons  # [(step,step)]
        self.causallinks = causallinks  # [(step,proposition,step)]
        self.openconds = openconds  # [(proposition,step)]
        self.threats = threats  # [causallink,threateningstep]
        self.hval = -1
        self.comment = ""  # for readability of output
        gensymplan = gensymplan + 1
        self.id = "plan" + str(gensymplan)  # for readability of output

# Cheap, readable self-documentation.
PROP_CL = 1     # prop of a CausalLink (s,p,s) is item 1
STEP1_CL = 0    # step1 of a CausalLink (s,p,s) is item 0
STEP2_CL = 2    # step2 of a CausalLink (s,p,s) is item 2
CL_THREAT = 0  # causal link of a threat ((s,p,s),s) is item 0
STEP_THREAT = 1  # step of a threat ((s,p,s),s) is item 1
PROP_OC = 0     # prop of an open condition (p,s) is item 0
STEP_OC = 1     # step of an open condition (p,s) is item 1


def printstate(state, wantcomment=1):
    print ""
    print state.id, "-----"
    if wantcomment and state.comment:
        print state.comment
    print "steps:", state.steps
    if state.causallinks:
        print "causal links:"
        for c in state.causallinks:
            print "  (%s < %s < %s)" % (c[0], c[1], c[2])
    if state.ordercons:
        print "ordering constraints (other than those",
        print "with goal or init):"
        none = 1
        for o in state.ordercons:
            if not o[0] == "init" and not o[0] == "goal"\
                    and not o[1] == "init" and not o[1] == "goal":
                none = 0
                break
        if none:
            print "none"
        else:
            for o in state.ordercons:
                if not o[0] == "init" and not o[0] == "goal"\
                        and not o[1] == "init" and not o[1] == "goal":
                    print "  (%s < %s)" % (o[0], o[1])
    if state.openconds:
        print "open conditions:"
        for o in state.openconds:
            print "  (%s, %s)" % (o[0], o[1])
    if state.threats:
        print "threats:"
        for t in state.threats:
            print "  ", t

#======================================
# Simple example 1.  From an assignment by Martha Pollack.
#
# Note: Under these definitions, the following is a valid plan:
#  sweep wash-floor dust, and the final world-state would
#    contain floor-dusty and floor-clean
#
preconds1 = {"init": [], "sweep": ["floor-dusty"],
             "vaccuum": ["floor-dusty", "vaccuum-works"],
             "wash-floor": ["floor-not-dusty", "floor-dirty"],
             "dust": ["furniture-dusty"]}

adds1 = {"goal": [], "sweep": ["floor-not-dusty"],
         "vaccuum": [], "wash-floor": ["floor-clean"],
         "dust": ["floor-dusty", "furniture-clean"]}

deletes1 = {"init": [], "goal": [], "sweep": ["floor-dusty"],
            "vaccuum": ["floor-dusty"], "wash-floor": ["floor-dirty"],
            "dust": ["floor-not-dusty", "furniture-dusty"]}

initprops1 = ["floor-dusty", "floor-dirty", "furniture-dusty", "vaccuum-works"]

goalprops1 = ["floor-clean", "furniture-clean", "floor-not-dusty"]
#=======================================
#blocks world example
##preconds = {"init":[]}
#
adds2 = {"goal": []}

deletes2 = {"init": [], "goal": []}

#initprops = ["on(a,table)","on(b,table)","on(c,table)",\
#             "clear(a)","clear(b)","clear(c)"]

#initprops = ["on(a,b)","on(b,table)","on(c,table)",\
#             "clear(a)","clear(c)"]

initprops2 = ["on(b,table)", "on(c,a)", "on(a,table)",
              "clear(b)", "clear(c)"]

#
#goalprops = ["on(a,b)","on(c,a)"]
#goalprops = ["on(b,c)","on(a,table)"]
goalprops2 = ["on(a,b)", "on(b,c)"]
#
preconds2 = {"init": []}
#
#
domain = ['a', 'b', 'c']
#
b = {}

for valset in perm(domain):
    varstr = "M" + valset[0] + "FROM" + valset[1] + "TO" + valset[2]
    b['x'] = valset[0]
    b['y'] = valset[1]
    b['z'] = valset[2]
    preconds2[varstr] =\
        [astr("on", [b['x'], b['y']]), astr("clear", [b['x']]), astr("clear", [b['z']])]
    adds2[varstr] =\
        [astr("on", [b['x'], b['z']]), astr("clear", [b['y']])]
    deletes2[varstr] =\
        [astr("on", [b['x'], b['y']]), astr("clear", [b['z']])]

b = {}

for valset in [['a', 'b'], ['a', 'c'], ['b', 'a'], ['b', 'c'], ['c', 'a'], ['c', 'b']]:
    b['x'] = valset[0]
    b['y'] = valset[1]
    varstr = "M" + b['x'] + "FROM" + b['y'] + "TOtable"
    preconds2[varstr] =\
        [astr("on", [b['x'], b['y']]), astr("clear", [b['x']])]
    adds2[varstr] =\
        [astr("on", [b['x'], "table"]), astr("clear", [b['y']])]
    deletes2[varstr] =\
        [astr("on", [b['x'], b['y']])]

b = {}

for valset in [['a', 'b'], ['a', 'c'], ['b', 'a'], ['b', 'c'], ['c', 'a'], ['c', 'b']]:
    b['x'] = valset[0]
    b['z'] = valset[1]
    varstr = "M" + b['x'] + "FROMtableTO" + b['z']
    preconds2[varstr] =\
        [astr("on", [b['x'], "table"]), astr("clear", [b['x']]), astr("clear", [b['z']])]
    adds2[varstr] =\
        [astr("on", [b['x'], b['z']])]
    deletes2[varstr] =\
        [astr("on", [b['x'], "table"]), astr("clear", [b['z']])]

#======================================
# The simple flat tire problem description from the text
#
preconds4 = {"init": [], "removeSpareTrunk": ["at(spare,trunk)"], "removeFlatAxle": ["at(flat,axle)"],
             "leaveOvernight": [], "putonSpareAxle": ["at(spare,ground)", "clear(axle)"]}

deletes4 = {"init": [], "goal": [],
            "removeSpareTrunk": ["at(spare,trunk)"], "removeFlatAxle": ["at(flat,axle)"],
            "leaveOvernight": ["at(spare,ground)", "at(spare,axle)", "at(spare,trunk)",
                               "at(flat,ground)", "at(flat,axle)"],
            "putonSpareAxle": ["at(spare,ground)", "clear(axle)"]}

adds4 = {"goal": [], "removeSpareTrunk": ["at(spare,ground)"],
         "removeFlAtAxle": ["at(flat,ground)", "clear(axle)"],
         "leaveOvernight": [], "putonSpareAxle": ["at(spare,axle)"]}

initprops4 = ["at(flat,axle)", "at(spare,trunk)"]

goalprops4 = ["at(spare,axle)"]

#==================================================
preconds5 = {"init": [], "put_in_casserole": ["clear(oven)", "not_cooked(casserole)"], "put_in_cake": ["clear(oven)", "not_cooked(cake)"],
            "put_in_microwave": ["clear(microwave)", "not_cooked(soup)"], "take_out_microwave": ["in_microwave(soup)"], "take_out_cake": ["in_oven(cake)"],
            "take_out_casserole": ["in_oven(casserole)"]}

deletes5 = {"init": [], "goal": [], "put_in_casserole": ["clear(oven)"], "put_in_cake": ["clear(oven)"],
            "put_in_microwave": ["clear(microwave)"], "take_out_microwave": ["in_microwave(soup)"], "take_out_cake": ["in_oven(cake)"],
            "take_out_casserole": ["in_oven(casserole)"]}

adds5 = {"init": [], "goal": [], "put_in_casserole": ["in_oven(casserole)"], "put_in_cake": ["in_oven(cake)"],
            "put_in_microwave": ["in_microwave(soup)"], "take_out_microwave": ["clear(microwave)", "cooked(soup)"], "take_out_cake": ["clear(oven)", "cooked(cake)"],
            "take_out_casserole": ["clear(oven)", "cooked(casserole)"]}

initprops5 = ['not_cooked(cake)', 'not_cooked(casserole)', 'not_cooked(soup)', 'clear(oven)', 'clear(microwave)']
goalprops5 = ['cooked(casserole)', 'cooked(soup)', 'cooked(cake)']


def printoperators():
    for a in adds.keys():
        if not a == "init" and not a == "goal":
            for p in preconds[a]:
                print p, " ",
            print ""
            printa = a
            while len(printa) < len("-------------------"):
                printa = " " + printa + " "
            print "-------------------"
            print printa
            print "-------------------"
            if adds[a] and not deletes[a]:
                for x in adds[a]:
                    print x, " ",
                print ""
            elif adds[a] and deletes[a]:
                for x in adds[a]:
                    print x, " ",
                for y in deletes[a]:
                    print "DEL:%s  " % (y),
                print ""
            elif deletes[a]:
                for y in deletes[a]:
                    print "DEL:%s  " % (y),
                print ""
        print " "
        print " "

#====== Functions defining the problem.


def goalp(node):
    # TODO: Add this back in.
    if len(node.state.openconds) + len(node.state.threats) == 0:
    #if len(node.state.openconds) == 0:
        return True
    else:
        return False
  # you define this


def h(node):
    #TODO: Add this in as well.
    hvals = len(node.state.openconds) + len(node.state.threats)
    # hvals = len(node.state.openconds)
    node.state.hval = hvals
    node.hval = hvals
    return hvals
  # you define this


def edgecost(state1, state2):
    return 1


def is_consistent(preceed_step, proceed_step, plan):
    """Checks to see if the consistency of the added order constraints
    is allowed."""
    bad_flag = True
    bad_flag &= not (proceed_step == 'init')
    bad_flag &= not (preceed_step == 'goal')
    # Checks for loops in the constraint orderings.
    closure_list = set()
    orderings = copy.deepcopy(plan.ordercons)
    changed = True
    while changed:
        changed = False
        for ordering in orderings:
            # Add proceed_step's greater orderings.
            if (ordering[0] == proceed_step or ordering[0] in closure_list) and ordering[1] not in closure_list:
                closure_list.add(ordering[1])
                changed = True

    # If preceed step is in the closure list, it is inconsistent
    bad_flag &= not (preceed_step in closure_list)
    return bad_flag


def find_actions(condition):
    """condition is of the form (proposition, step).
    Finds an action that will resolve this condition, if there is one."""
    for action in adds.keys():
        for proposition in adds[action]:
            if proposition == condition[PROP_OC]:
                yield action


def reuse_steps_for_condition(condition, steps):
    """Identify the possible steps we can use to do work."""
    for step in steps:
        # Break apart the number.
        splitz = step.split('*')
        actual_step = step if len(splitz) == 1 else splitz[1]
        for proposition in adds[actual_step]:
            if proposition == condition[PROP_OC]:
                yield step


def check_for_threat_inbetween(step_introduced, condition_info, plan):
    """Returns all the threats introduced by this new causal link"""
    condition, future_step = condition_info
    splitz = step_introduced.split('*')
    action_introduced = step_introduced if len(splitz) == 1 else splitz[1]
    conflicting_actions = set()
    for action in deletes:
        if condition in deletes[action]:
            conflicting_actions.add(action)

    # Gather all the conflicting steps!
    conflicting_steps = set()
    for step in plan.steps:
        splitz = step.split('*')
        # TODO: Make sure that it is on the action, not the step
        # that we need to check if they are the same thing for threats
        action = step if len(splitz) == 1 else splitz[1]
        if action in conflicting_actions and action != action_introduced:
            conflicting_steps.add(step)

    # Check to make sure that something isn't it.
    closure_after = set()
    orderings = copy.deepcopy(plan.ordercons)
    changed = True
    while changed:
        changed = False
        for ordering in orderings:
            # Add proceed_step's greater orderings.
            if (ordering[0] == future_step or ordering[0] in closure_after) and ordering[1] not in closure_after:
                closure_after.add(ordering[1])
                changed = True

    closure_before = set()
    changed = True
    while changed:
        changed = False
        for ordering in orderings:
            # Add proceed_step's greater orderings.
            if (ordering[1] == step_introduced or ordering[1] in closure_before) and ordering[0] not in closure_before:
                closure_before.add(ordering[0])
                changed = True

    # Do an intersection!
    print "BEFORE", closure_before
    print "AFTER:", closure_after
    total_closure = closure_after.union(closure_before)
    threats = []
    for confliction in conflicting_steps:
        if confliction not in total_closure and future_step != confliction:
            # It is a real threat.
            print "REAL THREAT FROM ADDING", step_introduced, " AND THREAT IS: ", confliction, " AND CONDITION:", condition
            new_threat = ((step_introduced, condition, future_step), confliction)
            threats.append(new_threat)

    return threats


def successors(node):
    success = []
    old_plan = node.state
    if old_plan.openconds:
        updated_conds = copy.deepcopy(old_plan.openconds)
        condition = updated_conds[0]
        updated_conds.remove(condition)
        print "Removing condition.. ", condition
        #Check current steps to see if we can use them..
        for step in reuse_steps_for_condition(condition, old_plan.steps):
            if not is_consistent(step, condition[STEP_OC], old_plan):
                continue
            updated_causes = copy.deepcopy(old_plan.causallinks)
            updated_ordering = copy.deepcopy(old_plan.ordercons)
            new_cause = (step, condition[PROP_OC], condition[STEP_OC])
            new_ordering = (step, condition[STEP_OC])
            updated_causes.append(new_cause)
            updated_ordering.append(new_ordering)
            updated_threats = copy.deepcopy(old_plan.threats)

            new_plan = Plan(steps=old_plan.steps, ordercons=updated_ordering,
                            openconds=updated_conds, causallinks=updated_causes, threats=updated_threats)
            new_plan.threats.extend(check_for_threat_inbetween(step, condition, new_plan))
            success.append(new_plan)
            print printstate(new_plan)

        #Add new steps.
        for action in find_actions(condition):
            # How is this even here?
            if action == "init":
                continue
            print "Action to take: ", action
            global gensymstep
            new_step = str(gensymstep) + "*" + action
            if new_step in old_plan.steps:
                gensymstep += 1
                new_step = str(gensymstep) + "*" + action
            if not is_consistent(new_step, condition[STEP_OC], old_plan):
                continue
            print "NEW STEP ADDED: ", new_step
            updated_steps = copy.deepcopy(old_plan.steps)
            updated_steps.append(new_step)
            updated_causes = copy.deepcopy(old_plan.causallinks)
            updated_ordering = copy.deepcopy(old_plan.ordercons)
            updated_threats = copy.deepcopy(old_plan.threats)
            new_cause = (new_step, condition[0], condition[1])

            init_ordering = ("init", new_step)
            goal_ordering = (new_step, "goal")
            updated_ordering.append(init_ordering)
            updated_ordering.append(goal_ordering)
            new_ordering = (new_step, condition[1])
            updated_causes.append(new_cause)
            updated_ordering.append(new_ordering)
            updated_open_cons = copy.deepcopy(updated_conds)
            for precond in preconds[action]:
                new_cond = (precond, new_step)
                print "Adding new condition: ", new_cond
                updated_open_cons.append(new_cond)
            new_plan = Plan(steps=updated_steps, ordercons=updated_ordering,
                            openconds=updated_open_cons, causallinks=updated_causes, threats=updated_threats)
            new_plan.threats.extend(check_for_threat_inbetween(new_step, condition, new_plan))
            success.append(new_plan)
            print printstate(new_plan)

    elif old_plan.threats:
        updated_threats = copy.deepcopy(old_plan.threats)
        print updated_threats
        causal_link_threat, threatening_step = updated_threats.pop()
        print causal_link_threat

        if is_consistent(threatening_step, causal_link_threat[0], old_plan):
            new_ordering = (threatening_step, causal_link_threat[0])
            old_plan = copy.deepcopy(old_plan)
            old_plan.ordercons.append(new_ordering)
            new_plan = Plan(steps=old_plan.steps, ordercons=old_plan.ordercons,
                        openconds=old_plan.openconds, causallinks=old_plan.causallinks,
                        threats=updated_threats)
            success.append(new_plan)
        if is_consistent(causal_link_threat[2], threatening_step, old_plan):
            new_ordering = (causal_link_threat[2], threatening_step)
            old_plan = copy.deepcopy(old_plan)
            old_plan.ordercons.append(new_ordering)
            new_plan = Plan(steps=old_plan.steps, ordercons=old_plan.ordercons,
                        openconds=old_plan.openconds, causallinks=old_plan.causallinks,
                        threats=updated_threats)
            success.append(new_plan)
        pass
    plan = node.state
    if printPlans:
        print "fringe[0].state:", plan.id, "---"
        printstate(plan)
        if node.parent:
            print "parent: plan", node.parent.state.id
    return success

    ### You define this.  This is where most of the work is.S

#======= The core search functions and auxiliaries


def treesearch(start, fringe):
    """Search through the successors of a problem to find a goal.

    start -- the start state
    fringe -- an empty queue
    Don't worry about repeated paths to a state """
    fringe.append(Node(start))
    while len(fringe) > 0:
        print "========="
        print "Len of fringe:", len(fringe)
        cur = fringe.pop()
        if goalp(cur):
            return cur
        if cur.depth >= INFINITY:
            print "** REACHED INFINITY; giving up"
            return []
        if printVerbose:
            print "VISITED:",
            cur.inlineprintNode()
        fringe.extend(makeNodes(cur, successors(cur)))
    return []


def graphsearch(start, fringe):
    """Search through the successors of a problem to find a goal.

    start -- the start state
    fringe -- an empty queue
    If a new path to a state is found, save the shortest-length path"""

    expanded = {}
    fringe.append(Node(start))
    while len(fringe) > 0:
        cur = fringe.pop()
        if cur.depth >= INFINITY:
            print "** REACHED INFINITY; giving up"
            return []
        # The next if statement is just for printing
        if printVerbose and cur.state in expanded:
            if expanded[cur.state].gval <= cur.gval:
                print "**New path to", cur.statename, "from", cur.parent.statename,\
                    "is NOT better than the old path to", cur.statename
            else:
                print "**New path to", cur.statename, "from", cur.parent.statename,\
                    "IS better than the old path to", cur.statename
        if not (cur.state in expanded and
                expanded[cur.state].gval <= cur.gval):
            expanded[cur.state] = cur
            if goalp(cur):
                return cur
            if printVerbose:
                print "VISITED:",
                cur.inlineprintNode()
            fringe.extend(makeNodes(cur, successors(cur)))
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
        # The following line was an error. When I wrote this, I thought
        # pop returned the first element of a list.  In fact, it
        # returns the last element of the list.
        # cur = fringe.pop()
        cur = fringe[0]
        fringe = fringe[1:]
        if goalp(cur):
            return cur
        if printVerbose:
            print "VISITED:",
            cur.inlineprintNode()
        if cur.depth <= depthlim:
            fringe = makeNodes(cur, successors(cur)) + fringe
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
        cur = fringe[0]
        fringe = fringe[1:]
        curF = cur.gval + h(cur)
        if goalp(cur):
            return (cur, curF)
        if curF <= fLim:
            if printVerbose:
                print "VISITED:",
                cur.inlineprintNode()
            succNodes = makeNodes(cur, successors(cur))
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
        cur = fringe[0]
        fringe = fringe[1:]
        if goalp(cur):
            return cur
        if cur.depth >= INFINITY:
            print "** REACHED INFINITY; giving up"
            return []
        if printVerbose:
            print "LEN(FRINGE):", len(fringe), "VISITED:",
            cur.inlineprintNode()
        newnodes = makeNodes(cur, successors(cur))
        for s in newnodes:
            fringe = insertByH(s, fringe)
        fringe = fringe[:beamwidth]
    return []


#==== Main program.  Change this as appropriate for your application.
#====
#====

print sys.argv


def planfun(initprops, goalprops):
    print ""
    print "initial props:", initprops
    print "goal props:", goalprops
    print ""
    adds["init"] = initprops
    preconds["goal"] = goalprops
    initialstate = Plan(steps=["init", "goal"], openconds=[(x, "goal") for x in goalprops])
    printstate(initialstate)
    return Astar(initialstate)

#===============
gensymnode = 0

gensymplan = 0

gensymstep = 0


def printPathToNode(node):
    if node:
        node.printNode()
        if node.parent:
            printPathToNode(node.parent)


def printStatesOnPathToNode(node):
    n = node
    states = []
    while n:
        states = states + [n.state]
        n = n.parent
    states.reverse()
    for s in states:
        printstate(s)

#=========================================
#
maxdepth = 15
INFINITY = 35
printVerbose = 0
printPlans = 1

if len(sys.argv) > 1:
    exec("initprops = initprops" + str(sys.argv[1]))
    exec("goalprops = goalprops" + str(sys.argv[1]))
    exec("preconds = preconds" + str(sys.argv[1]))
    exec("adds = adds" + str(sys.argv[1]))
    exec("deletes = deletes" + str(sys.argv[1]))
else:
    initprops = initprops2
    goalprops = goalprops2
    preconds = preconds2
    adds = adds2
    deletes = deletes2
printoperators()

result = planfun(initprops, goalprops)
print ""
print "Result:**********************"
print ""
printStatesOnPathToNode(result)
