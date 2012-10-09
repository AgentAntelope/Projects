def valuePerPiece(node, space, player):
    cur_board = node.state.board
    pieces = 0
    king_pieces = 0
    enemy_pieces = 0
    enemy_king_pieces = 0
    total_pieces = 0

    PIECE_MULTIPLIER = 1.0
    KING_MULTIPLIER = 2.0

    opponent = OppDic1[player]
    for i in range(8):
        for j in range(8):
            #number of the king and man
            if cur_board[i][j] == player:
                pieces += 1
                total_pieces += 1
            elif cur_board[i][j] == PieceToKingDic[player]:
                king_pieces += 1
                total_pieces += 1
            elif cur_board[i][j] == opponent:
                enemy_pieces += 1
                total_pieces += 1
            elif cur_board[i][j] == PieceToKingDic[opponent]:
                enemy_king_pieces += 1
                total_pieces += 1

    player_val = PIECE_MULTIPLIER * pieces/total_pieces + KING_MULTIPLIER * king_pieces/total_pieces
    enemy_val = PIECE_MULTIPLIER * enemy_pieces/total_pieces + KING_MULTIPLIER * enemy_king_pieces/total_pieces
    return (player_val - enemy_val)

def value_per_row(node, space, player):
    cur_board = node.state.board
    pieces_val = {'r': 0, 'b': 0}
    total_pieces = 0
    opponent = OppDic1[player]
    for i in range(8):
        for j in range(8):
            if cur_board[i][j] == 'r':
                pieces_val['r'] +=  1.5*abs(7 - i) + 5
            elif cur_board[i][j] == 'b':
                pieces_val['b'] += (1.5*i + 5)
            elif cur_board[i][j] == 'B':
                pieces_val['b'] += (1.5*i + 8)
            elif cur_board[i][j] == 'R':
                pieces_val['r'] += 1.5*abs(7 - i) + 8
    player_val = pieces_val[player]
    enemy_pieces_val = pieces_val[opponent]
    return (player_val - enemy_pieces_val)


def kings_move(node, space, player):
    cur_board = node.state.board
    pieces_val = {'r': 0, 'b': 0}
    opponent = OppDic1[player]
    player_kings_value = 0
    enemy_kings_value = 0
    # Add up all the kings
    for i in range(8):
        for j in range(8):
            if cur_board[i][j] == PieceToKingDic[player]:
                player_kings_value += 40
            elif cur_board[i][j] == PieceToKingDic[opponent]:
                enemy_kings_value += 40;

    return player_kings_value - enemy_kings_value

def is_forced_move(successors):
    for successor in successors:
        origin = successor.move[0]
        next_move = successor.move[1]
        # If it is a forced move, a jump will occur which takes 2 columns and 2 rows
        return abs(origin[0] - next_move[0]) != 1

PieceToKingDic = {'r':'R', 'b':'B'}
OppDic = {'B':['r','R'],'R':['b','B'],'b':['r','R'],'r':['r','R']}
PlayerDic = {'r':['r','R'],'b':['b','B'],'R':['r','R'],'B':['b','B']}
OppDic1 = {'b':'r','r':'b'}
piece_row_goal = {'b':7, 'r': 0}