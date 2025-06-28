   // Selecionando os botões e formulários
        const btnInserir = document.querySelector('#btn-inserir-tarefa');
        const btnEditar = document.querySelector('#btn-editar-tarefa');
        const inserirTarefa = document.querySelector('#inserir-tarefa');
        const editarTarefa = document.querySelector('#editar-tarefa');
        const acoesEditar = document.querySelector('#acoes-inserir');
        const acoesInserir = document.querySelector('#acoes-editar');

        // Função para controlar a exibição do formulário Inserir
        btnInserir.addEventListener('click', () => {
            if (!btnInserir.classList.contains('collapsed')) {

                acoesInserir.classList.remove('d-none');

                if(btnEditar.classList.contains('btn-outline-warning')){
                //remover estilo do botao editar, caso usuario clique no botao inserir
                  btnEditar.classList.remove('btn-outline-warning');
                  btnEditar.classList.add('btn-warning');
                }


                //alterar estilo do botao inserir para ativado
                btnInserir.classList.remove('btn-success');
                btnInserir.classList.add('btn-outline-success');


              if(editarTarefa.classList.contains('show')){
                 editarTarefa.classList.remove('show');
              }

              if(!acoesEditar.classList.contains('d-none')){
                acoesEditar.classList.add('d-none');
              }
            }else if(btnInserir.classList.contains('collapsed')){
                acoesInserir.classList.add('d-none');

                if(btnInserir.classList.contains('btn-outline-success')){
                  
                  //alterar estilo do botao inserir para desativado
                  btnInserir.classList.remove('btn-outline-success');
                  btnInserir.classList.add('btn-success');
                }
            }
        });


        // Função para controlar a exibição do formulário Editar
        btnEditar.addEventListener('click', () => {
            if (!btnEditar.classList.contains('collapsed')) {

                acoesEditar.classList.remove('d-none');

                //remover estilo do botao inserir, caso usuario clique no botao editar
                if(btnInserir.classList.contains('btn-outline-success')){
                  btnInserir.classList.remove('btn-outline-success');
                  btnInserir.classList.add('btn-success');
                }

                //alterar estilo do botao editar para ativado
                btnEditar.classList.remove('btn-warning');
                btnEditar.classList.add('btn-outline-warning');

              if(inserirTarefa.classList.contains('show')){
                 inserirTarefa.classList.remove('show');
              }

              if(!acoesInserir.classList.contains('d-none')){
                acoesInserir.classList.add('d-none');
              }
            }else if(btnEditar.classList.contains('collapsed')){
                acoesEditar.classList.add('d-none');

                if(btnEditar.classList.contains('btn-outline-warning')){
                  //alterar estilo do botao editar para desativado
                  btnEditar.classList.remove('btn-outline-warning');
                  btnEditar.classList.add('btn-warning');
                }

            }
            
        });

        //CheckBox
            // Obter todos os checkboxes
            const checkboxes = document.querySelectorAll('#select-consulta');

            // Adicionar um ouvinte de evento para cada checkbox
            checkboxes.forEach((checkbox) => {
            checkbox.addEventListener('change', function () {
            // Quando um checkbox é marcado, desmarque os outros
            checkboxes.forEach((otherCheckbox) => {
                if (otherCheckbox !== this) {
                    otherCheckbox.checked = false; // Desmarcar outros checkboxes
                    // Remover a classe de destaque de todas as linhas
                    otherCheckbox.closest('tr').classList.remove('table-primary');
                }
            });

            // Encontrar a linha pai do checkbox (tr)
            const row = this.closest('tr');

            // Adicionar ou remover a classe 'table-primary' dependendo do estado do checkbox
            if (this.checked) {
            row.classList.add('table-primary');
            } else {
            row.classList.remove('table-primary');
            }
            });
            });