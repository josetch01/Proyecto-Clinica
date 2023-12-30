const url = "/sistemaginecofem/api/citas/";

function save(bandera) {
    $("#modal-update").modal("hide")
    let id = $("#guardar").data("id");    
    let cita = {
        id: id,
        fecha_cita : $("#fecha").val(),
        historia : $("#historia").val(),
        paciente : {
					id: $("#paciente_id").val()
				},
        medico : {
					id: $("#medico_id").val()
				},
        producto : {
					id: $("#servicio_id").val()
				},
        estado :{
					id: $("#estado_id").val()
				},

    }
    let metodo = bandera == 1 ? "POST" : "PUT";
    $.ajax({
        type: metodo,
        url: url,
        data: JSON.stringify(cita),
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
                t.row.add([value.id, value.fecha_cita,value.historia,value.producto.precio,value.paciente.nombre+" "+value.paciente.apellidos+"<br>"+" ( "+value.paciente.documento+" )",value.medico.nombre+" "+value.medico.apellidos+"<br>"+" ( "+value.medico.documento+" )",value.producto.nombre,value.estado.nombre, botonera]);
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
            $("#modal-title").text("Editar Cita");
            $("#fecha").val(data.fecha);
            $("#historia").val(data.historia);
            $("#paciente_id").val(data.paciente.id);
            $("#medico_id").val(data.medico.id);
            $("#servicio_id").val(data.producto.id);
            $("#estado_id").val(data.estado.id);
            $("#guardar").data("id", data.id);
            $("#guardar").data("bandera", 0);
            $("#modal-update").modal("show");
        },
    }).fail(function () {

    });
}

function clear() {
    $("#modal-title").text("Nueva Cita");
    $("#fecha").val("");
    $("#historia").val("");
    $("#guardar").data("id", 0);
    $("#guardar").data("bandera", 1);
}
function getSelect(){
	
	    
    $.ajax({
        type: "GET",
        url: url+"productos",
        cache: false,
        timeout: 600000,
        success: function (data) {
			$("#servicio_id").empty();
			for( var i = 0; i<data.length; i++){
				var id = data[i]['id'];
				var nombre = data[i]['nombre'];						
				$("#servicio_id").append("<option value='"+id+"'>"+nombre+"</option>");	
			}
        },
    }).fail(function () {
        
    });
	$.ajax({
        type: "GET",
        url: url+"pacientes",
        cache: false,
        timeout: 600000,
        success: function (data) {
			$("#paciente_id").empty();
			for( var i = 0; i<data.length; i++){
				var id = data[i]['id'];
				var nombre = data[i]['nombre'];		
				var apellidos = data[i]['apellidos'];	
				var documento = data[i]['documento'];		
				$("#paciente_id").append("<option value='"+id+"'>"+nombre+" "+apellidos+"( "+documento+" )"+"</option>");	
			}
        },
    }).fail(function () {
        
    });
    
    $.ajax({
        type: "GET",
        url: url+"medicos",
        cache: false,
        timeout: 600000,
        success: function (data) {
			$("#medico_id").empty();
			for( var i = 0; i<data.length; i++){
				var id = data[i]['id'];
				var nombre = data[i]['nombre'];		
				var apellidos = data[i]['apellidos'];	
				var documento = data[i]['documento'];				
				$("#medico_id").append("<option value='"+id+"'>"+nombre+" "+apellidos+"( "+documento+" )"+"</option>");	
			}
        },
    }).fail(function () {
        
    });

    
    $.ajax({
        type: "GET",
        url: url+"estados",
        cache: false,
        timeout: 600000,
        success: function (data) {
			$("#estado_id").empty();
			for( var i = 0; i<data.length; i++){
				var id = data[i]['id'];
				var nombre = data[i]['nombre'];						
				$("#estado_id").append("<option value='"+id+"'>"+nombre+"</option>");	
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
            { targets: 1, width: "10%" },
            { targets: 2, width: "10%" },
            { targets: 3, width: "10%" },
            { targets: 4, width: "20%" },
            { targets: 5, width: "20%" },
            { targets: 6, width: "10%" },
            { targets: 7, width: "20%" },
            { targets: 8, orderable: false, width: "10%" }
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
            title: 'Eliminar Cita',
            text: "¿Esta seguro de querer eliminar esta cita?",
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
    $('#liConsulta').addClass("menu-open");  
    $('#aConsulta').addClass("active");
    $('#liCitas').addClass("active");
    getSelect()
});