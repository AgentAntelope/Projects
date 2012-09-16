from utils import *
import copy

PUZZLE_ROWS = 3
PUZZLE_COLS = 3
EMPTY_TILE = 0

########################################################################
#########################8 PUZZLE CODE #################################
def pattern_database():
	

def check_cell(row, col, goal_puzzle_state, cell_value):
    if goal_puzzle_state[row][col] == cell_value:
        return row, col
    else:
        return False


def find_cell(goal_puzzle_state, cell_value):
    for checked_cell in iterate_tiles(check_cell, goal_puzzle_state, cell_value):
        if checked_cell:
            return checked_cell


def single_tile_distance(row, col, current_puzzle_state, goal_puzzle_state):
    current_value = current_puzzle_state[row][col]
    goal_row, goal_col = find_cell(goal_puzzle_state, current_value)
    tile_distance = abs(goal_row - row) + abs(goal_col - col)
    return tile_distance


def manhatten_distance(current_puzzle_state, goal_puzzle_state):
    return sum(
        [tile_distance for tile_distance in iterate_tiles(
            single_tile_distance, current_puzzle_state,
            goal_puzzle_state)])


def check_tile_equivalence(row, col, current_puzzle_state, goal_puzzle_state):
    """Return 0 if the tiles are equal, otherwise 1"""
    return 0 if current_puzzle_state[row][col] == goal_puzzle_state[row][col] else 1


def tiles_out_of_place(current_puzzle_state, goal_puzzle_state):
    return sum([tiles_equal for tiles_equal in iterate_tiles(check_tile_equivalence, current_puzzle_state, goal_puzzle_state)])


def iterate_tiles(func_iterator, *args):
    for row in range(PUZZLE_ROWS):
        for col in range(PUZZLE_COLS):
            yield func_iterator(row, col, *args)


def goalp(current_puzzle_state, goal_puzzle_state):
    return tiles_out_of_place(current_puzzle_state, goal_puzzle_state) == 0


def allow_successor(target_row, target_col):
    return (target_row >= 0
            and target_row < PUZZLE_ROWS
            and target_col >= 0
            and target_col < PUZZLE_COLS)


def add_successor(puzzle_state, successors, source_row, source_col, target_row, target_col):
    if allow_successor(target_row, target_col):
        new_puzzle_state = copy.deepcopy(puzzle_state)
        new_puzzle_state[source_row][source_col] = puzzle_state[
            target_row][target_col]
        new_puzzle_state[target_row][target_col] = puzzle_state[
            source_row][source_col]
        successors.append(new_puzzle_state)


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

a = [
[1, 2, 3],
[4, 0, 6],
[7, 8, 5]]

b = [
[1, 2, 3],
[4, 5, 6],
[7, 8, 0]]

print_puzzle(a)
