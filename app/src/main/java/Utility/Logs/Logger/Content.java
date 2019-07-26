package Utility.Logs.Logger;

import Utility.Logs.BaseContent;

public class Content extends BaseContent {
    private boolean fresh = true;

    @Override
    public void log(String content) {
        if (fresh) {
            this.logs.clear();
            this.logs.add(content);

            fresh = false;

            if (getFeed() != null) {
                getFeed().feedRebuilt();
            }
        } else {
            super.log(content);
        }
    }

    @Override
    public void refreshIntent() {
        super.refreshIntent();

        fresh = true;
    }
}
