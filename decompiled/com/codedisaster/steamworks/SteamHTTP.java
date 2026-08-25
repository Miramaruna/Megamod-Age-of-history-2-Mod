package com.codedisaster.steamworks;

import java.nio.ByteBuffer;

public class SteamHTTP extends SteamInterface {
   public SteamHTTP(SteamHTTPCallback callback) {
      this(SteamAPI.getSteamHTTPPointer(), createCallback(new SteamHTTPCallbackAdapter(callback)));
   }

   SteamHTTP(long pointer, long callback) {
      super(pointer, callback);
   }

   public SteamHTTPRequestHandle createHTTPRequest(SteamHTTP.HTTPMethod requestMethod, String absoluteURL) {
      return new SteamHTTPRequestHandle(createHTTPRequest(this.pointer, requestMethod.ordinal(), absoluteURL));
   }

   public boolean setHTTPRequestContextValue(SteamHTTPRequestHandle request, long contextValue) {
      return setHTTPRequestContextValue(this.pointer, request.handle, contextValue);
   }

   public boolean setHTTPRequestNetworkActivityTimeout(SteamHTTPRequestHandle request, int timeoutSeconds) {
      return setHTTPRequestNetworkActivityTimeout(this.pointer, request.handle, timeoutSeconds);
   }

   public boolean setHTTPRequestHeaderValue(SteamHTTPRequestHandle request, String headerName, String headerValue) {
      return setHTTPRequestHeaderValue(this.pointer, request.handle, headerName, headerValue);
   }

   public boolean setHTTPRequestGetOrPostParameter(SteamHTTPRequestHandle request, String paramName, String paramValue) {
      return setHTTPRequestGetOrPostParameter(this.pointer, request.handle, paramName, paramValue);
   }

   public SteamAPICall sendHTTPRequest(SteamHTTPRequestHandle request) {
      return new SteamAPICall(sendHTTPRequest(this.pointer, this.callback, request.handle));
   }

   public SteamAPICall sendHTTPRequestAndStreamResponse(SteamHTTPRequestHandle request) {
      return new SteamAPICall(sendHTTPRequestAndStreamResponse(this.pointer, request.handle));
   }

   public int getHTTPResponseHeaderSize(SteamHTTPRequestHandle request, String headerName) {
      return getHTTPResponseHeaderSize(this.pointer, request.handle, headerName);
   }

   public boolean getHTTPResponseHeaderValue(SteamHTTPRequestHandle request, String headerName, ByteBuffer value) throws SteamException {
      if (!value.isDirect()) {
         throw new SteamException("Direct buffer required!");
      } else {
         return getHTTPResponseHeaderValue(this.pointer, request.handle, headerName, value, value.position(), value.remaining());
      }
   }

   public int getHTTPResponseBodySize(SteamHTTPRequestHandle request) {
      return getHTTPResponseBodySize(this.pointer, request.handle);
   }

   public boolean getHTTPResponseBodyData(SteamHTTPRequestHandle request, ByteBuffer data) throws SteamException {
      if (!data.isDirect()) {
         throw new SteamException("Direct buffer required!");
      } else {
         return getHTTPResponseBodyData(this.pointer, request.handle, data, data.position(), data.remaining());
      }
   }

   public boolean getHTTPStreamingResponseBodyData(SteamHTTPRequestHandle request, int bodyDataOffset, ByteBuffer data) throws SteamException {
      if (!data.isDirect()) {
         throw new SteamException("Direct buffer required!");
      } else {
         return getHTTPStreamingResponseBodyData(this.pointer, request.handle, bodyDataOffset, data, data.position(), data.remaining());
      }
   }

   public boolean releaseHTTPRequest(SteamHTTPRequestHandle request) {
      return releaseHTTPRequest(this.pointer, request.handle);
   }

   private static native long createCallback(SteamHTTPCallbackAdapter var0);

   private static native long createHTTPRequest(long var0, int var2, String var3);

   private static native boolean setHTTPRequestContextValue(long var0, long var2, long var4);

   private static native boolean setHTTPRequestNetworkActivityTimeout(long var0, long var2, int var4);

   private static native boolean setHTTPRequestHeaderValue(long var0, long var2, String var4, String var5);

   private static native boolean setHTTPRequestGetOrPostParameter(long var0, long var2, String var4, String var5);

   private static native long sendHTTPRequest(long var0, long var2, long var4);

   private static native long sendHTTPRequestAndStreamResponse(long var0, long var2);

   private static native int getHTTPResponseHeaderSize(long var0, long var2, String var4);

   private static native boolean getHTTPResponseHeaderValue(long var0, long var2, String var4, ByteBuffer var5, int var6, int var7);

   private static native int getHTTPResponseBodySize(long var0, long var2);

   private static native boolean getHTTPResponseBodyData(long var0, long var2, ByteBuffer var4, int var5, int var6);

   private static native boolean getHTTPStreamingResponseBodyData(long var0, long var2, int var4, ByteBuffer var5, int var6, int var7);

   private static native boolean releaseHTTPRequest(long var0, long var2);

   public static enum HTTPMethod {
      Invalid,
      GET,
      HEAD,
      POST,
      PUT,
      DELETE,
      OPTIONS;
   }

   public static enum HTTPStatusCode {
      Invalid(0),
      Continue(100),
      SwitchingProtocols(101),
      OK(200),
      Created(201),
      Accepted(202),
      NonAuthoritative(203),
      NoContent(204),
      ResetContent(205),
      PartialContent(206),
      MultipleChoices(300),
      MovedPermanently(301),
      Found(302),
      SeeOther(303),
      NotModified(304),
      UseProxy(305),
      TemporaryRedirect(307),
      BadRequest(400),
      Unauthorized(401),
      PaymentRequired(402),
      Forbidden(403),
      NotFound(404),
      MethodNotAllowed(405),
      NotAcceptable(406),
      ProxyAuthRequired(407),
      RequestTimeout(408),
      Conflict(409),
      Gone(410),
      LengthRequired(411),
      PreconditionFailed(412),
      RequestEntityTooLarge(413),
      RequestURITooLong(414),
      UnsupportedMediaType(415),
      RequestedRangeNotSatisfiable(416),
      ExpectationFailed(417),
      Unknown4xx(418),
      TooManyRequests(429),
      InternalServerError(500),
      NotImplemented(501),
      BadGateway(502),
      ServiceUnavailable(503),
      GatewayTimeout(504),
      HTTPVersionNotSupported(505),
      Unknown5xx(599);

      private final int code;
      private static final SteamHTTP.HTTPStatusCode[] values = values();

      private HTTPStatusCode(int code) {
         this.code = code;
      }

      static SteamHTTP.HTTPStatusCode byValue(int statusCode) {
         for (SteamHTTP.HTTPStatusCode value : values) {
            if (value.code == statusCode) {
               return value;
            }
         }

         return Invalid;
      }
   }
}
