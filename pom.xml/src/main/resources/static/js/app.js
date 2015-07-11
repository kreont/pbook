$(function() {

	// Адрес, по которому обращаемся к REST API
	var restURL = '/persons';

	// Преобразование POST данных в JSON
	var postToJson = function(postdata) {
		postdata._search = false;
		delete postdata.oper;
		if (postdata.id === '_empty') {
			delete postdata.id;
		}
		return JSON.stringify(postdata);
	};
	var PBook = {

		start : function() {

			$.extend($.jgrid.edit, {
				closeAfterEdit : true,
				closeAfterAdd : true,
				mtype : 'PUT',
				ajaxEditOptions : {
					type : "PUT",
					contentType : "application/json; charset=utf-8",
					dataType : "json"
				},
				serializeEditData : function(data) {
					console.log("EDIT - PUT");
					delete data.oper;
					return JSON.stringify(data);
				}
			});

			$("#jqGrid").jqGrid({

				jsonReader : {
					root : "rows",
					page : "page",
					total : "total",
					records : "records",
					repeatitems : false,
					cell : "cell",
					id : "id"
				},
				url : restURL,
				autowidth : true,
				multiselect : false,
				mtype : "GET",
				datatype : "json",
				ajaxGridOptions : {
					contentType : "application/json; charset=utf-8",
					dataType : "json"
				},
				caption : 'Телефонная книга',
				colModel : [ {
					label : 'id',
					name : 'id',
					hidden : true,
					key : true,
					width : 75
				}, {
					label : 'ФИО',
					name : 'fullName',
					editable : true,
					width : 75
				}, {
					label : 'Телефон',
					name : 'phone',
					editable : true,
					width : 150
				}, {
					label : 'Адрес',
					name : 'address',
					editable : true,
					width : 150
				} ],
				width : 500,
				height : 'auto',
				rowNum : 30,
				viewrecords : true,
				sortname : 'id',
				sortorder : "asc",
				pager : "#jqGridPager",
				url : restURL,
				editurl : restURL,
				ajaxRowOptions : {
					type : "PUT",
					contentType : "application/json",
					dataType : "json"
				},
				serializeRowData : postToJson
			});

			$('#jqGrid').jqGrid('filterToolbar', {
				stringResult : true,
				searchOnEnter : true,
				// По умолчанию используем поиск по подстроке
				defaultSearch : "cn"
			});
			$('#jqGrid').navGrid('#jqGridPager',
			// Вывод управляющих кнопок в тулбар
			{
				edit : true,
				add : true,
				del : true,
				search : false,
				refresh : true,
				view : false,
				position : "left",
				cloneToTop : false
			},
			// Опции редактирования
			{
				editCaption : "Редактирование записи",
				recreateForm : true,
				checkOnUpdate : true,
				checkOnSubmit : true,
				closeAfterEdit : true,
				ajaxEditOptions : {
					type : "PUT",
					contentType : "application/json; charset=utf-8",
					dataType : "json"
				},
				onclickSubmit : function(params, postdata) {
					params.url = restURL + '/' + postdata.jqGrid_id;
				},
				serializeEditData : postToJson,
				errorTextFormat : function(data) {
					return 'Error: ' + data.responseText
				}
			},
			// Опции добавления
			{
				closeAfterAdd : true,
				recreateForm : true,
				mtype : "POST",
				ajaxEditOptions : {
					type : "POST",
					contentType : "application/json; charset=utf-8",
					dataType : "json"
				},
				serializeEditData : postToJson,
				errorTextFormat : function(data) {
					return 'Error: ' + data.responseText
				}
			},

			// Опции удаления
			{
				ajaxDelOptions : {
					type : "DELETE",
					contentType : "application/json; charset=utf-8",
					dataType : "json"
				},
				serializeDelData : postToJson,
				errorTextFormat : function(data) {
					return 'Error: ' + data.responseText
				},
				onclickSubmit : function(params, postdata) {
					params.url = restURL + '/' + postdata;
				}
			}, {
				// Операторы поиска
				sopt : [ 'cn', 'eq', 'ne', 'lt', 'gt', 'bw', 'ew' ],
				closeOnEscape : true,
				multipleSearch : true,
				closeAfterSearch : true
			});
		}
	}
	// Запуск приложения
	$(document).ready(PBook.start);
});