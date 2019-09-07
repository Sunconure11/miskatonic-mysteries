package moriyashiine.acme.common.capability;

import moriyashiine.acme.util.SanityUtil;

public class Sanity implements ISanity {
    private int sanity; //150 is for now the standard

    public Sanity() {
        this.sanity = SanityUtil.SANITY_MAX;
    }

    @Override
    public int getSanity() {
        return sanity;
    }

    @Override
    public void setSanity(int sanity) {
        this.sanity = sanity;
    }
}
