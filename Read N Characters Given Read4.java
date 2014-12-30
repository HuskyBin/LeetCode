/*
The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function will only be called once for each test case.

Show Tags
*/
public class Read4 {
    public int read(char[] buf, int n) {
        if (buf == null || n <= 0) {
            return 0;
        }
        char[] buffer = new char[4];
        int readTotal = 0;
        int size = 0;
        boolean isEnd = false;
        while (isEnd == false && readTotal < n) {
            size = read4(buffer);
            if (size < 4) {
                isEnd = true;
            }
            int bytes = Math.min(n - readTotal, size);
            for (int i = 0; i < bytes; i++) {
                buf[readTotal + i] = buffer[i];
            }
            readTotal += bytes;
        }
        return readTotal;
    }
}
