/*---------------+DATATABLE+---------------*/
$('#example').DataTable({
    layout: {
        topStart: {
            search: {
                placeholder: 'Pesquise aqui...'
            }
        },
        topEnd: {

            pageLength: {
                menu: [ 10, 25, 50, 100 ],
            },
            buttons:['copy', 'csv', 'excel', 'pdf', 'print']
        },
        bottomEnd: {
            paging: {
                numbers: 3
            }
        }
    },
    /*Definindo a linguagem do datatable para PT-BR */
    language: {
        url: '/assets/data/datatable-pt-BR.json'
    }

})

