$(document).ready(function() {
    $.fn.initBootTableVisi = function() {
        $(this).bootstrapTable('destroy');
        $(this).bootstrapTable().
        unbind('check.bs.table').on('check.bs.table', function (e, row) {
            consObjeAlum([{name : 'codiObjeAlum', value : row.id.trim()}]);
        });
        return false;
    };
    $.fn.initBootTableEmpl = function() {
        $(this).bootstrapTable('destroy');
        $(this).bootstrapTable().
        unbind('check.bs.table').on('check.bs.table', function (e, row) {
            consObjeAlum([{name : 'codiObjeAlum', value : row.id.trim()}]);
        });
        return false;
    };
});
function INIT_OBJE_TABL_VISI(){
    $("#TablVisi").initBootTableVisi();
}
function INIT_OBJE_TABL_EMPL(){
    $("#TablEmpl").initBootTableEmpl();
}
