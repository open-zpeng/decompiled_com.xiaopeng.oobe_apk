package com.xiaopeng.xui.widget.prompt;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import com.xiaopeng.xpui.R;
@Deprecated
/* loaded from: classes2.dex */
public class XPrompt extends Prompt {
    private ViewGroup mHostView;

    public XPrompt(@NonNull Context context) {
        super(context);
    }

    @UiThread
    public static XPrompt makePrompt(@NonNull Activity activity) {
        return makePrompt(activity, findHostViewFromActivity(activity));
    }

    @UiThread
    public static XPrompt makePrompt(@NonNull Activity activity, @NonNull CharSequence charSequence) {
        return makePrompt(activity, charSequence, 0);
    }

    @UiThread
    public static XPrompt makePrompt(@NonNull Activity activity, @NonNull CharSequence charSequence, int i) {
        return makePrompt(activity, findHostViewFromActivity(activity), charSequence, i);
    }

    @UiThread
    public static XPrompt makePrompt(@NonNull Context context, @NonNull ViewGroup viewGroup, @NonNull CharSequence charSequence, int i) {
        XPrompt makePrompt = makePrompt(context, viewGroup);
        makePrompt.addMessage(new XPromptMessage(i, charSequence));
        return makePrompt;
    }

    private static ViewGroup findHostViewFromActivity(@NonNull Activity activity) {
        return (ViewGroup) activity.findViewById(16908290);
    }

    public static XPrompt makePrompt(@NonNull Context context, @NonNull ViewGroup viewGroup) {
        XPromptView xPromptView = (XPromptView) viewGroup.findViewById(R.id.x_prompt);
        XPrompt xPrompt = xPromptView != null ? (XPrompt) xPromptView.getPrompt() : null;
        if (xPrompt == null) {
            xPrompt = new XPrompt(context);
        }
        xPrompt.setHostView(viewGroup);
        return xPrompt;
    }

    @UiThread
    public XPrompt setHostView(@NonNull Activity activity) {
        setHostView(findHostViewFromActivity(activity));
        return this;
    }

    @UiThread
    public XPrompt setHostView(@NonNull ViewGroup viewGroup) {
        ViewGroup viewGroup2 = this.mHostView;
        if (viewGroup2 != null && !viewGroup2.equals(viewGroup)) {
            this.mHostView.removeView(this.mXPromptView);
        }
        this.mHostView = viewGroup;
        return this;
    }

    @Override // com.xiaopeng.xui.widget.prompt.Prompt
    protected boolean addView() {
        ViewGroup viewGroup = this.mHostView;
        if (viewGroup != null) {
            viewGroup.addView(this.mXPromptView);
            return true;
        }
        return false;
    }

    @Override // com.xiaopeng.xui.widget.prompt.Prompt
    protected void removeView() {
        if (this.mXPromptView.getParent() instanceof ViewGroup) {
            ((ViewGroup) this.mXPromptView.getParent()).removeView(this.mXPromptView);
        }
    }
}
