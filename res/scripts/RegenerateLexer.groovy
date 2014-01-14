package scripts

File file = new File('intellij/src/monet/tara/intellij/metamodel/TaraLexer.java')
fileText = file.text;
file.write fileText.replace('zzBufferL[zzCurrentPosL++]', 'zzBufferL.charAt(zzCurrentPosL++)');
println "Lexer regenerated"