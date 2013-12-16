File file = new File('src/monet/tara/metamodel/TaraLexer.java')
fileText = file.text;
fileText = fileText.replace('zzBufferL[zzCurrentPosL++]', 'zzBufferL.charAt(zzCurrentPosL++)')
file.write fileText;