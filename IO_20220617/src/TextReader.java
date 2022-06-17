import java.io.*;
public class TextReader {

	public static void main(String[] args) throws IOException {
		InputStream in = new FileInputStream("c:/tmp/demo.txt");
		InputStreamReader ir = new InputStreamReader(in,"utf-8");
		BufferedReader br = new BufferedReader(ir);
		
		/*
		 	1. InputStream：開 Stream 檔案
		 	2. InputStreamReader：讀檔案，但是是用 char 讀取
		 	3. BufferedReader：將 InputStreamReader 轉成用 String 讀取
		*/
		
		String line = null;		
		
		while((line = br.readLine())!=null) {
			System.out.println(line);
		}
		
		br.close();
		in.close();

	}

}
