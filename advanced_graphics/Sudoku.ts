/**
 *
 * Created with JetBrains WebStorm.
 * User: sean
 * Date: 4/19/13
 * Time: 7:35 PM
 * To change this template use File | Settings | File Templates.
 */

    /// <reference path="jquery.d.ts" />

class Sudoku {
    init_array: number[][];
    constructor(initial_array: number[][]) {
        this.init_array = initial_array;
    }

    getCell(x: number, y: number){

    }
}

class Cell {
    chosen_num: number;
    hints: number[];
    constructor(){
        this.chosen_num = 0;
        this.hints = [1, 2, 3, 4];
    }

    removeHint(num: number){
        this.hints[num-1] = 0;
    }

    addHint(num: number){
        this.hints[num-1] = num;
    }

    chooseNum(num: number){
        this.chosen_num = num;
    }

    unchooseNum(num: number){
        this.chosen_num = 0;
    }

    chosenNum():number {
        return this.chosen_num;
    }

    isChosen():bool{
        return this.chosen_num != 0;
    }
}

class Board {
    groups: number[][];
    init_cells:Cell[];

    constructor(arr: number[][]){
        this.init_cells = [];
        var count = 0;
        for(var i:number = 0; i < arr.length; i++)
        {
            for(var j:number = 0; j < arr[i].length; j++)
            {
                var tempCell = new Cell();
                tempCell.chooseNum(arr[i][j]);
                this.init_cells[count] = tempCell;
                count++;
            }
        }
    }

    createGroups4x4(){
        this.groups = [];
        //Tables
        this.groups[0] = [0,1,2,3];
        this.groups[1] = [4,5,6,7];
        this.groups[2] = [8,9,10,11];
        this.groups[3] = [12,13,14,15];
        //Rows
        this.groups[4] = [0,1,4,5];
        this.groups[5] = [2,3,6,7];
        this.groups[6] = [8,9,12,13];
        this.groups[7] = [10,11,14,15];
        //Columns
        this.groups[8] = [0,2,8,10];
        this.groups[9] = [1,3,9,11];
        this.groups[10] = [4,6,12,14];
        this.groups[11] = [5,7,13,15];
    }


}

function displayTable(board:Board){

}
function displayCell(cell: Cell, element:JQuery){
    var str:string = "";

    if(cell.isChosen()){
        str = "<div class='chosen'>" + cell.chosenNum() + "</div>";
        var ele = $(str);
        ele.appendTo(element);

    }
    else{
        for(var i:number = 0; i < cell.hints.length; i++){
            if(cell.hints[i] != 0){
                str +=  "<div class='selectable'>" + cell.hints[i] + "</div>";
                var ele = $(str);

                var retCell = function(cell:Cell, num:number){
                    return function(){
                        alert(""+ num);
                    }
                }
                ele.click(retCell(cell, i));
                ele.appendTo(element);
            }
        }

    }
    return str;
}
$(document).ready(function(){

var arr:number[][] = new Array(new Array(0, 0, 0, 1),
    new Array(0, 0, 0, 0),
    new Array(3, 0, 0, 0),
    new Array(0, 0, 2, 0));
var board:Board = new Board(arr);

displayCell(board.init_cells[0], $("body"));
})
