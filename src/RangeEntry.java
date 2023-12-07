public class RangeEntry implements Comparable<RangeEntry> {
    Long sourceStart;
    Long range;
    Long destStart;

    public RangeEntry(Long sourceStart, Long range, Long destStart) {
        this.sourceStart = sourceStart;
        this.range = range;
        this.destStart = destStart;
    }
    @Override
    public int compareTo(RangeEntry other) {
        return Long.compare(this.sourceStart, other.sourceStart);
    }
    public String toString(){
        return "RangeEntry:{sourceStart= " +sourceStart + ", range= " + range + ", destStart= " + destStart + "}\n";
    }
}
