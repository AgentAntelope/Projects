var Sudoku = (function () {
    function Sudoku(initial_array) {
        this.init_array = initial_array;
    }
    Sudoku.prototype.getCell = function (x, y) {
    };
    return Sudoku;
})();

var Cell = (function () {
    function Cell() {
        this.chosen_num = 0;
        this.hints = [1, 2, 3, 4];
    }
    Cell.prototype.removeHint = function (num) {
        this.hints[num - 1] = 0;
    };
    Cell.prototype.addHint = function (num) {
        this.hints[num - 1] = num;
    };
    Cell.prototype.chooseNum = function (num) {
        this.chosen_num = num;
    };
    Cell.prototype.unchooseNum = function (num) {
        this.chosen_num = 0;
    };
    Cell.prototype.chosenNum = function () {
        return this.chosen_num;
    };
    Cell.prototype.isChosen = function () {
        return this.chosen_num != 0;
    };
    return Cell;
})();


var Board = (function () {
    function Board(arr) {
        this.init_cells = [];
        var count = 0;
        for(var i = 0; i < arr.length; i++) {
            for(var j = 0; j < arr[i].length; j++) {
                var tempCell = new Cell();
                tempCell.chooseNum(arr[i][j]);
                this.init_cells[count] = tempCell;
                count++;
            }
        }

    }
    Board.prototype.createGroups4x4 = function () {
        this.groups = [];
        this.groups[0] = [0, 1, 2, 3];
        this.groups[1] = [4, 5, 6, 7];
        this.groups[2] = [8, 9, 10, 11];
        this.groups[3] = [12, 13, 14, 15];
        this.groups[4] = [0, 1, 4, 5];
        this.groups[5] = [2, 3, 6, 7];
        this.groups[6] = [8, 9, 12, 13];
        this.groups[7] = [10, 11, 14, 15];
        this.groups[8] = [0, 2, 8, 10];
        this.groups[9] = [1, 3, 9, 11];
        this.groups[10] = [4, 6, 12, 14];
        this.groups[11] = [5, 7, 13, 15];

        for(i = 0; i < this.init_cells.length; i++){
            if(this.init_cells[i].isChosen()){
                this.updateCell(this.init_cells[i], this.init_cells[i].chosenNum())
            }
        }
    };

    Board.prototype.updateCell = function (cell, selected) {
        this.init_cells[this.lookupCell(cell)].chooseNum(selected);
        for(var i = 0; i < this.groups.length; i++) {
            for(var j = 0; j < this.groups.length; j++) {
                if(this.lookupCell(cell) == this.groups[i][j]) {
                    for(var n = 0; n < this.groups[i].length; n++) {
                        this.init_cells[this.groups[i][n]].removeHint(selected);
                    }
                }
            }
        }

        if(this.checkWin()){
            alert("HOLY SHIT WE WON");
            location.reload();
            var arr = new Array(new Array(0, 0, 0, 1), new Array(0, 0, 0, 0), new Array(3, 0, 0, 0), new Array(0, 0, 2, 0));
            var B = new Board(arr);

        }

    };
    Board.prototype.undoSingleCell = function(cell){
        var groups = this.getGroups(cell);
        cell.addHint(1);
        cell.addHint(2);
        cell.addHint(3);
        cell.addHint(4);
        for(var i = 0; i < groups.length; i++){
            var group = groups[i];

            for(var j = 0; j < group.length; j++){
                var otherCell = this.init_cells[group[j]];
                if(cell != otherCell && otherCell.isChosen()){
                    cell.removeHint(otherCell.chosenNum());
                }
            }
        }
    }

    Board.prototype.undoCell = function(cell){
        cell.unchooseNum(0);
        this.undoSingleCell(cell);
        var groups = this.getGroups(cell);
        for(var i = 0; i < groups.length; i++){
            var group = groups[i];
            for(var j = 0; j < group.length; j++){
                var otherCell = this.init_cells[group[j]];
                if(cell != otherCell && !otherCell.isChosen()){
                    this.undoSingleCell(otherCell);
                }
            }
        }
    }
    Board.prototype.checkWin = function(){
        var i = 0;
        for(i = 0; i < this.init_cells.length; i++){
            if(!this.init_cells[i].isChosen()){
                return false;
            }
        }
        return true;

    }

    Board.prototype.lookupCell = function (cell) {
        for(var i = 0; i < this.init_cells.length; i++) {
            if(this.init_cells[i] == cell) {
                return i;
            }
        }
        return 0;
    };
    Board.prototype.getGroups = function (cell) {
        var ingroup = [];
        var count = 0;
        for(var i = 0; i < this.groups.length; i++) {
            for(var j = 0; j < this.groups.length; j++) {
                if(this.lookupCell(cell) == this.groups[i][j]) {
                    ingroup[count] = this.groups[i];
                    count++;
                }
            }
        }
        return ingroup;
    }

    return Board;
})();


function displayCell(cell, element) {
    var str = "";
    if(cell.isChosen()) {
        str = "<div class='chosen'>" + cell.chosenNum() + "</div>";
        var ele = $(str);
        ele.appendTo(element);
    } else {
        for(var i = 0; i < cell.hints.length; i++) {
            if(cell.hints[i] != 0) {
                str = "<div class='selectable'>" + cell.hints[i] + "</div>";
                var ele = $(str);
                var retCell = function (cell, num) {
                    return function () {
                        $('#sudoku').empty();
                        B.updateCell(cell, num);
                        displayTable(B);
                        undo.push(cell);
                        $("#undo").removeAttr("disabled");
                    }
                };
                ele.click(retCell(cell, cell.hints[i]));
                ele.appendTo(element);
            }
        }
    }
    return str;
}


function displayTable(board) {
    var count = 0;
    var table = $("<table border=1 width='35%' height='60%'></table>");
    for(var m = 0; m < 2; m++) {
        var row = $("<tr></tr>");
        for(var n = 0; n < 2; n++) {
            var tabledata = $("<td></td>");
            var table_within = $('<table border=1 width="100%" height="100%"></table>');
            var tempval = n;
            if(m == 1) {
                tempval = n + 2;
            }
            for(var j = 0; j < 2; j++) {
                var tr = $("<tr></tr>");
                for(var i = 0; i < 2; i++) {
                    var temp = i;
                    if(j == 1) {
                        temp = i + 2;
                    }
                    var final_td = $("<td></td>");
                    displayCell(board.init_cells[count], final_td);

                    count++;
                    final_td.appendTo(tr);
                }
                tr.appendTo(table_within);
            }
            table_within.appendTo(tabledata);
            tabledata.appendTo(row);
        }
        row.appendTo(table);
    }
    table.appendTo($('#sudoku'));
}

$(document).ready(function(){
    $("#undo").attr("disabled", true);

    $("#undo").click(function(){
        var cell = undo.pop();
        B.undoCell(cell);
        $("#sudoku").empty();
        displayTable(B);
        if(undo.length == 0){
            $("#undo").attr("disabled", true);
        }
    });
    B.createGroups4x4();
    displayTable(B);

})

var undo = [];
var arr = new Array(new Array(0, 0, 0, 1), new Array(0, 0, 0, 0), new Array(3, 0, 0, 0), new Array(0, 0, 2, 0));
var B = new Board(arr);