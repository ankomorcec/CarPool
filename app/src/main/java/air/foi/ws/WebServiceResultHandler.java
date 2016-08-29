package air.foi.ws;

public interface WebServiceResultHandler {
    public void handleResult(
            String result,
            boolean ok,
            long timestamp
    );
}
