import java.io.*;
public class TextReader {

	public static void main(String[] args) throws IOException {
		InputStream in = new FileInputStream("c:/tmp/demo.txt");
		InputStreamReader ir = new InputStreamReader(in,"utf-8");
		BufferedReader br = new BufferedReader(ir);
		
		/*
		 	1. InputStream�G�} Stream �ɮ�
		 	2. InputStreamReader�GŪ�ɮסA���O�O�� char Ū��
		 	3. BufferedReader�G�N InputStreamReader �ন�� String Ū��
		*/
		
		String line = null;		
		
		while((line = br.readLine())!=null) {
			System.out.println(line);
		}
		
		br.close();
		in.close();

	}

}
