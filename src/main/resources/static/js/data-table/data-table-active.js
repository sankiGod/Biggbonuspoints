(function($) {
	"use strict";

	var $table = $('#table');
	$('#toolbar').find('select').change(function() {
		$table.bootstrapTable('destroy').bootstrapTable({
			exportDataType : $(this).val()
		});
	});

	var $table1 = $('#table1');
	$('#toolbar').find('select').change(function() {
		$table.bootstrapTable('destroy').bootstrapTable({
			exportDataType : $(this).val()
		});
	});
	
	var $table2 = $('#table2');
	$('#toolbar').find('select').change(function() {
		$table.bootstrapTable('destroy').bootstrapTable({
			exportDataType : $(this).val()
		});
	});

})(jQuery);