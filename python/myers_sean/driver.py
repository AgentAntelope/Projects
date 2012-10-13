from eight_puzzle import successors, print_puzzle, manhatten_distance_heuristic, tiles_out_of_place_heuristic, row_column_heuristic
from search import Node

start_state = (
    (1, 2, 3),
    (7, 0, 4),
    (8, 6, 5),
)
start_node = Node(start_state)
print "Test showing that 8-Puzzle is right."
print "The starting state is the following:"
print_puzzle(start_state)
print "---------------------------------------"
print "The successors are as follows:"
for successor in successors(start_node):
    print_puzzle(successor)

print "---------------------------------------"
print "From the start state, the only difference from goal is that the 7 and 8 are mixed."
print "This means manhatten distance should be 2, same for tiles out of place and column-row."
print "Manhatten Distance: ", manhatten_distance_heuristic(start_node)
print "Tiles out of Place: ", tiles_out_of_place_heuristic(start_node)
print "Column-Row: ", row_column_heuristic(start_node)

