public class SequenceResult {
    private boolean succeeded;
    private String result;

    public SequenceResult(boolean succeeded, String result) {
        this.succeeded = succeeded;
        this.result = result;
    }

    public boolean succeeded() {
        return succeeded;
    }
    public String message() {
        return result;
    }
}
