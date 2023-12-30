const url = "/sistemaginecofem/api/medicos/";

function save(bandera) {
    $("#modal-update").modal("hide")
    let id = $("#guardar").data("id");    
    let medico = {
        id: id,
        nombre : $("#nombre").val(),
        apellidos : $("#apellidos").val(),
        documento : $("#documento").val(),  
        telefono : $("#telefono").val(), 
        nacimiento : $("#nacimiento").val(), 
        consultorio :{
					id: $("#consultorio_id").val()
				},
         especialidad :{
					id: $("#especialidad_id").val()
				},

    }
    let metodo = bandera == 1 ? "POST" : "PUT";
    $.ajax({
        type: metodo,
        url: url,
        data: JSON.stringify(medico),
        dataType: "text",
        contentType: "application/json",
        cache: false,
        success: function (data) {
			if(data==0){
				Swal.fire({
	                icon: 'error',
	                title: 'Ya existe un registro con ese Documento',
	                showConfirmButton: false,
	                timer: 1500
	            })				
			}else{
	            let texto = bandera == 1 ? "guardado": "actualizado";
	            getTabla();
	            Swal.fire({
	                icon: 'success',
	                title: 'Se ha '+texto+' el registro',
	                showConfirmButton: false,
	                timer: 1500
	            })
	            clear();
            }
        },
    }).fail(function () {
        
    });
}

function deleteFila(id) {
    $.ajax({
        type: "DELETE",
        url: url + id,
        cache: false,
        timeout: 600000,
        success: function (data) {
            Swal.fire({
                icon: 'success',
                title: 'Se ha eliminado el registro',
                showConfirmButton: false,
                timer: 1500
            });
            getTabla();
        },
    }).fail(function () {

    });

}

function getTabla() {
    $.ajax({
        type: "GET",
        url: url,
        dataType: "text",
        cache: false,
        success: function (data) {
            let t = $("#tablaListado").DataTable();
            t.clear().draw(false);
            let botonera = '<button type="button" class="btn btn-warning btn-sm editar"><i class="fas fa-edit"></i> </button>' +
                '<button type="button" class="btn btn-danger btn-sm eliminar"><i class="fas fa-trash"></i></button>';
            let js = JSON.parse(data);

            $.each(js, function (index, value) {
                t.row.add([value.id, value.nombre+" "+value.apellidos,value.nacimiento,value.documento,value.telefono,value.especialidad.nombre,value.consultorio.nombre+" - "+value.consultorio.codigo,value.estado, botonera]);
            });
            t.draw(false);
        },
    }).fail(function () {

    });
}


function getFila(id) {
    $.ajax({
        type: "GET",
        url: url + id,
        cache: false,
        timeout: 600000,
        success: function (data) {
            $("#modal-title").text("Editar Paciente");
            $("#nombre").val(data.nombre);
            $("#apellidos").val(data.apellidos);
            $("#nacimiento").val(data.nacimiento);
            $("#documento").val(data.documento);
            $("#telefono").val(data.telefono);
            $("#especialidad_id").val(data.especialidad.id);
            $("#consultorio_id").val(data.consultorio.id);
            $("#guardar").data("id", data.id);
            $("#guardar").data("bandera", 0);
            $("#modal-update").modal("show");
        },
    }).fail(function () {

    });
}

function clear() {
    $("#modal-title").text("Nuevo Paciente");
    $("#nombre").val("");
    $("#apellidos").val("");
    $("#nacimiento").val("");
    $("#documento").val("");
    $("#telefono").val("");
    $("#direccion").val("");
    $("#ciudad").val("");
    $("#guardar").data("id", 0);
    $("#guardar").data("bandera", 1);
}

function getSelect(){
	$.ajax({
        type: "GET",
        url: url+"consultorios",
        cache: false,
        timeout: 600000,
        success: function (data) {
			$("#consultorio_id").empty();
			for( var i = 0; i<data.length; i++){
				var id = data[i]['id'];
				var nombre = data[i]['nombre'];		
				var codigo = data[i]['codigo'];			
				$("#consultorio_id").append("<option value='"+id+"'>"+nombre+" - "+codigo+"</option>");	
			}
        },
    }).fail(function () {
        
    });
    
    $.ajax({
        type: "GET",
        url: url+"especialidades",
        cache: false,
        timeout: 600000,
        success: function (data) {
			$("#especialidad_id").empty();
			for( var i = 0; i<data.length; i++){
				var id = data[i]['id'];
				var nombre = data[i]['nombre'];				
				$("#especialidad_id").append("<option value='"+id+"'>"+nombre+"</option>");	
			}
        },
    }).fail(function () {
        
    });
}

$(document).ready(function () {
    $("#tablaListado").DataTable({
        language: {
            lengthMenu: "Mostrar _MENU_ registros",
            zeroRecords: "No se encontraron coincidencias",
            info: "Mostrando del _START_ al _END_ DE _TOTAL_",
            infoEmpty: "Sin resultados",
            search: "Buscar: ",
            paginate: {
                first: "Primero",
                last: "Último",
                next: "Siguiente",
                previous: "Anterior",
            },
        },
        columnDefs: [
            { targets: 0, width: "10%" },
            { targets: 1, width: "20%" },
            { targets: 2, width: "10%" },
            { targets: 3, width: "10%" },
            { targets: 4, width: "20%" },
            { targets: 5, width: "20%" },
            { targets: 6, width: "20%" },
            { targets: 7, orderable: false, width: "10%" }
        ],
    });

    clear();

    $("#nuevo").click(function () {
        clear();
    });

    $("#guardar").click(function () {
        let bandera = $("#guardar").data("bandera");
        save(bandera);
    })

    $(document).on('click', '.eliminar', function () {
        Swal.fire({
            title: 'Eliminar Paciente',
            text: "¿Esta seguro de querer eliminar este Paciente?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Si'
        }).then((result) => {
            if (result.isConfirmed) {
                let id = $($(this).parents('tr')[0].children[0]).text();
                deleteFila(id);
            }
        })
    });

    $(document).on('click', '.editar', function () {
        let id = $($(this).parents('tr')[0].children[0]).text();
        getFila(id);
    });
    getTabla();
    $('#liMantenimiento').addClass("menu-open");  
    $('#aMantenimiento').addClass("active");
    $('#liMedicos').addClass("active");
    getSelect()
});