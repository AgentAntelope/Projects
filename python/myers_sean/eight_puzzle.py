from utils import *
import copy

PUZZLE_ROWS = 3
PUZZLE_COLS = 3
EMPTY_TILE = 0

row_places = {}
col_places = {}

GOAL_PUZZLE_STATE = (
    (1, 2, 3),
    (8, 0, 4),
    (7, 6, 5)
)
########################################################################
######################### ROW-COLUMN HEURISTIC #################################
row_values = {
    1: 0, 2: 0, 3: 0, 4: 1, 5: 2, 6: 2, 7: 2, 8: 1, 0: 1
}

col_values = {
    1: 0, 2: 1, 3: 2, 4: 2, 5: 2, 6: 1, 7: 0, 8: 0, 0: 1
}


def row_column_heuristic(current_puzzle_node):
    current_puzzle_state = current_puzzle_node.state
    total = sum([tile_counts for tile_counts in iterate_tiles(off_outputs, current_puzzle_state)])
    current_puzzle_node.hval = total
    return total


def off_outputs(row, col, current_puzzle_state):
    tile_value = current_puzzle_state[row][col]
    row_tile_val = 1 if row_values[tile_value] != row else 0
    col_tile_val = 1 if col_values[tile_value] != col else 0
    return row_tile_val + col_tile_val

############################MANHATTEN DISTANCE FUNCTIONS #########################################


def check_cell(row, col, goal_puzzle_state, cell_value):
    """If the row and column have that cell value, return them. Else false."""
    if goal_puzzle_state[row][col] == cell_value:
        return row, col
    else:
        return False


def find_cell(goal_puzzle_state, cell_value):
    """Finds the cell where the cellvalue is located at and returns it."""
    for checked_cell in iterate_tiles(check_cell, goal_puzzle_state, cell_value):
        if checked_cell:
            return checked_cell


def single_tile_distance(row, col, current_puzzle_state, goal_puzzle_state):
    """Gets manhatten distance for a single tile."""
    current_value = current_puzzle_state[row][col]
    goal_row, goal_col = find_cell(goal_puzzle_state, current_value)
    tile_distance = abs(goal_row - row) + abs(goal_col - col)
    return tile_distance


def manhatten_distance_heuristic(current_puzzle_node):
    current_puzzle_state = current_puzzle_node.state
    ret = sum(
        [tile_distance for tile_distance in iterate_tiles(
            single_tile_distance, current_puzzle_state,
            GOAL_PUZZLE_STATE)])
    current_puzzle_node.hval = ret
    return ret

############################TILES OUT OF PLACE HEURISTIC FUNCTIONS##############################


def tiles_out_of_place(current_puzzle_state):
    return sum([tiles_equal for tiles_equal in iterate_tiles(check_tile_equivalence, current_puzzle_state, GOAL_PUZZLE_STATE)])


def tiles_out_of_place_heuristic(current_puzzle_node):
    heuristic_value = tiles_out_of_place(current_puzzle_node.state)
    current_puzzle_node.hval = heuristic_value
    return heuristic_value


#############HELPER FUNCTIONS ########################################
def iterate_tiles(func_iterator, *args):
    for row in range(PUZZLE_ROWS):
        for col in range(PUZZLE_COLS):
            yield func_iterator(row, col, *args)


def check_tile_equivalence(row, col, current_puzzle_state, goal_puzzle_state):
    """Return 0 if the tiles are equal, otherwise 1"""
    return 0 if current_puzzle_state[row][col] == goal_puzzle_state[row][col] else 1


#######################SUCCESSOR FUNCTIONS CODE ##############################
def allow_successor(target_row, target_col):
    """Is it a legit successor?"""
    return


def add_successor(puzzle_state, successors, source_row, source_col, target_row, target_col):
    if (target_row >= 0 and target_row < PUZZLE_ROWS
            and target_col >= 0 and target_col < PUZZLE_COLS):

        new_puzzle_state = swap_cells(puzzle_state, source_row, source_col, target_row, target_col)
        successors.append(new_puzzle_state)


def swap_cells(puzzle_state, source_row, source_col, target_row, target_col):
    """Due to weird things like lists not being hashable, this swaps a tuple
    by changing it to a list, swapping the elements and the changing back."""
    auxillary_list = [[0, 0, 0], [0, 0, 0], [0, 0, 0]]
    for row in range(PUZZLE_ROWS):
        for col in range(PUZZLE_COLS):
            auxillary_list[row][col] = puzzle_state[row][col]

    auxillary_list[source_row][source_col] = puzzle_state[
        target_row][target_col]
    auxillary_list[target_row][target_col] = puzzle_state[
        source_row][source_col]

    return tuple(tuple(auxillary_list[i]) for i in range(PUZZLE_ROWS))


def goalp(current_puzzle_node):
    return tiles_out_of_place(current_puzzle_node.state) == 0


def successors(puzzle):
    puzzle_state = puzzle.state
    all_successors = []
    row, col = find_cell(puzzle_state, EMPTY_TILE)
    add_successor(puzzle_state, all_successors, row, col, row + 1, col)
    add_successor(puzzle_state, all_successors, row, col, row - 1, col)
    add_successor(puzzle_state, all_successors, row, col, row, col + 1)
    add_successor(puzzle_state, all_successors, row, col, row, col - 1)
    return all_successors


def edgecost(source, target):
    return 1


def print_puzzle(puzzle_state):
    print
    for row in range(PUZZLE_ROWS):
        print '| ',
        for col in range(PUZZLE_COLS):
            if puzzle_state[row][col] is EMPTY_TILE:
                print '-  ',
            else:
                print puzzle_state[row][col], ' ',
        print '|'
    print
