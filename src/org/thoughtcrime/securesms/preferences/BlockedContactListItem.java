package org.thoughtcrime.securesms.preferences;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.thoughtcrime.securesms.R;
import org.thoughtcrime.securesms.components.AvatarImageView;
import org.thoughtcrime.securesms.recipients.Recipients;

public class BlockedContactListItem extends RelativeLayout implements Recipients.RecipientsModifiedListener {

  private AvatarImageView contactPhotoImage;
  private TextView        nameView;
  private Recipients      recipients;

  public BlockedContactListItem(Context context) {
    super(context);
  }

  public BlockedContactListItem(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public BlockedContactListItem(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  public void onFinishInflate() {
    super.onFinishInflate();
    this.contactPhotoImage = (AvatarImageView)findViewById(R.id.contact_photo_image);
    this.nameView          = (TextView)       findViewById(R.id.name);
  }

  public void set(Recipients recipients) {
    this.recipients = recipients;

    onModified(recipients);
    recipients.addListener(this);
  }

  @Override
  public void onModified(Recipients recipients) {
    this.contactPhotoImage.setAvatar(recipients.getPrimaryRecipient(), false);
    this.nameView.setText(recipients.toShortString());
  }

  public Recipients getRecipients() {
    return recipients;
  }
}
