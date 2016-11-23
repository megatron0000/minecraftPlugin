# minecraftPlugin

Plugin para integrar o site ao jogo. Por enquanto, tem só alguns comandos e eventos "in-game"

## 0.0.1

- HttpMessenger
- Comando basic (adiciona, lê e deleta chave=>conteúdo desejadas)
- Comando getbau em modo teste (http get a página pré-estabelecida e retorna o resultado textual)

## 0.1.0

- Comando warp (cria local de teleporte para posterior uso; deleta warp desejado; lista warps existentes)
- Evento customizado: PlayerWarpEvent (lançado quando do teleporte por meio do comando warp)

## 0.1.1

- Implementação de permissão para usar getBau e para usar Signs recuperadoras de vida
- Signs com a escrita "[cura]" recuperam vida ao clicar com botão direito