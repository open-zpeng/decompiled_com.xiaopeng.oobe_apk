package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.util.Log;
import android.util.Xml;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public class KeyFrames {
    private static final String CUSTOM_ATTRIBUTE = "CustomAttribute";
    private static final String CUSTOM_METHOD = "CustomMethod";
    private static final String TAG = "KeyFrames";
    public static final int UNSET = -1;
    static HashMap<String, Constructor<? extends Key>> sKeyMakers = new HashMap<>();
    private HashMap<Integer, ArrayList<Key>> mFramesMap = new HashMap<>();

    static {
        try {
            sKeyMakers.put("KeyAttribute", KeyAttributes.class.getConstructor(new Class[0]));
            sKeyMakers.put(TypedValues.PositionType.NAME, KeyPosition.class.getConstructor(new Class[0]));
            sKeyMakers.put(TypedValues.CycleType.NAME, KeyCycle.class.getConstructor(new Class[0]));
            sKeyMakers.put("KeyTimeCycle", KeyTimeCycle.class.getConstructor(new Class[0]));
            sKeyMakers.put(TypedValues.TriggerType.NAME, KeyTrigger.class.getConstructor(new Class[0]));
        } catch (NoSuchMethodException e) {
            Log.e(TAG, "unable to load", e);
        }
    }

    public void addKey(Key key) {
        if (!this.mFramesMap.containsKey(Integer.valueOf(key.mTargetId))) {
            this.mFramesMap.put(Integer.valueOf(key.mTargetId), new ArrayList<>());
        }
        ArrayList<Key> arrayList = this.mFramesMap.get(Integer.valueOf(key.mTargetId));
        if (arrayList != null) {
            arrayList.add(key);
        }
    }

    public KeyFrames() {
    }

    public KeyFrames(Context context, XmlPullParser parser) {
        Key key = null;
        try {
            int eventType = parser.getEventType();
            while (eventType != 1) {
                if (eventType != 0) {
                    if (eventType == 2) {
                        String name = parser.getName();
                        if (sKeyMakers.containsKey(name)) {
                            try {
                                Constructor<? extends Key> constructor = sKeyMakers.get(name);
                                if (constructor != null) {
                                    Key newInstance = constructor.newInstance(new Object[0]);
                                    try {
                                        newInstance.load(context, Xml.asAttributeSet(parser));
                                        addKey(newInstance);
                                        key = newInstance;
                                    } catch (Exception e) {
                                        e = e;
                                        key = newInstance;
                                        Log.e(TAG, "unable to create ", e);
                                        eventType = parser.next();
                                    }
                                } else {
                                    throw new NullPointerException("Keymaker for " + name + " not found");
                                    break;
                                }
                            } catch (Exception e2) {
                                e = e2;
                            }
                        } else if (name.equalsIgnoreCase("CustomAttribute")) {
                            if (key != null && key.mCustomConstraints != null) {
                                ConstraintAttribute.parse(context, parser, key.mCustomConstraints);
                            }
                        } else if (name.equalsIgnoreCase("CustomMethod") && key != null && key.mCustomConstraints != null) {
                            ConstraintAttribute.parse(context, parser, key.mCustomConstraints);
                        }
                    } else if (eventType == 3) {
                        if (ViewTransition.KEY_FRAME_SET_TAG.equals(parser.getName())) {
                            return;
                        }
                    }
                }
                eventType = parser.next();
            }
        } catch (IOException e3) {
            e3.printStackTrace();
        } catch (XmlPullParserException e4) {
            e4.printStackTrace();
        }
    }

    public void addAllFrames(MotionController motionController) {
        ArrayList<Key> arrayList = this.mFramesMap.get(-1);
        if (arrayList != null) {
            motionController.addKeys(arrayList);
        }
    }

    public void addFrames(MotionController motionController) {
        ArrayList<Key> arrayList = this.mFramesMap.get(Integer.valueOf(motionController.mId));
        if (arrayList != null) {
            motionController.addKeys(arrayList);
        }
        ArrayList<Key> arrayList2 = this.mFramesMap.get(-1);
        if (arrayList2 != null) {
            Iterator<Key> it = arrayList2.iterator();
            while (it.hasNext()) {
                Key next = it.next();
                if (next.matches(((ConstraintLayout.LayoutParams) motionController.mView.getLayoutParams()).constraintTag)) {
                    motionController.addKey(next);
                }
            }
        }
    }

    static String name(int viewId, Context context) {
        return context.getResources().getResourceEntryName(viewId);
    }

    public Set<Integer> getKeys() {
        return this.mFramesMap.keySet();
    }

    public ArrayList<Key> getKeyFramesForView(int id) {
        return this.mFramesMap.get(Integer.valueOf(id));
    }
}
