package age.of.civilizations2.jakowski.lukasz;

import java.io.ByteArrayInputStream;
import java.io.EOFException;

public class BytesUtil {
   public static int getInt(ByteArrayInputStream stream) throws EOFException {
      int ch1 = stream.read();
      int ch2 = stream.read();
      int ch3 = stream.read();
      int ch4 = stream.read();
      if ((ch1 | ch2 | ch3 | ch4) < 0) {
         throw new EOFException();
      } else {
         return (ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0);
      }
   }
}
