/*
The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function may be called multiple times.
*/
/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    private int count = 0;
    private int point = 0;
    private char[] buff = new char[4];
    public int read(char[] buf, int n) {
        if (buf == null || n < 0) {
            return -1;
        }
        int totalByte = 0;
        boolean isEnd = false;
        while (!isEnd && totalByte < n) {
            if (point == 0) {
                count = read4(buff);
            }
            while (totalByte < n && point < count) {
                buf[totalByte++] = buff[point++];
            }
            if (count < 4) isEnd = true;
            if (point == count) point = 0;
        }
        return totalByte;
    }
}
