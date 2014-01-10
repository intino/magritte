package scripts

File file = new File('src/monet/tara/metamodel/TaraLexer.java')
fileText = file.text;
file.write fileText.replace('zzBufferL[zzCurrentPosL++]', 'zzBufferL.charAt(zzCurrentPosL++)');
println "Lexer regenerated"