#language: pt

Funcionalidade: Validação do site da Unimed

Cenario: Validar uma sequência de ações

Dado que o usuário acesse o site "https://www.unimed.coop.br/"
Quando acessar o Guia Médico
E realizar uma pesquisa de médicos no "Rio de Janeiro"
Então será validado a apresentação do resultados com a Especialidade e Cidade
| especialidade  | cidade |
| Clínica Médica | RJ     |



Cenario: Validar acesso ao Guia Médico, pesquisa de médicos no Rio de Janeiro
		 e não apresentação dos resultados com cidade São Paulo nas três primeiras 
		 páginas do resultado de pesquisa
		 
Dado que o usuário acesse o site "https://www.unimed.coop.br/"
Quando acessar o "Guia Médico", o acesso será validado
E realizar uma pesquisa de médicos no "Rio de Janeiro"
Então será validado a não apresentação dos resultados com cidade de São Paulo nas três primeiras páginas
| especialidade  | cidade |
| Clínica Médica | SP     |



