package tr.ee.util.mailsender;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@ApiModel
public final class InternalEmail implements Serializable {
    @NotNull
    @Email
    @ApiModelProperty(notes = "The e-mail address of the sender")
    private String from;
    @NotNull
    @Email
    @ApiModelProperty(notes = "The e-mail address of the receiver")
    private String to;
    @Email
    @ApiModelProperty(notes = "The e-mail address that should receive replies")
    private String replyTo;
    @NotNull
    @ApiModelProperty(notes = "The e-mail subject")
    private String subject;
    @ApiModelProperty(notes = "The e-mail HTML content")
    private String htmlBody;
    @ApiModelProperty(notes = "The list of e-mail file attachment identifiers")
    private List<UUID> uuidList;

    @NotNull
    @ApiModelProperty(notes = "The first name of the sender")
    private String firstName;


    @NotNull
    @ApiModelProperty(notes = "The last name of the sender")
    private String lastName;

    public InternalEmail() {

    }

    public InternalEmail(@NotNull @Email String from, @NotNull @Email String to, @Email String replyTo, @NotNull String subject, String htmlBody, List<UUID> uuidList, @NotNull @Email String firstName, @NotNull @Email String lastName) {
        this.from = from;
        this.to = to;
        this.replyTo = replyTo;
        this.subject = subject;
        this.htmlBody = htmlBody;
        this.uuidList = uuidList;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public String getSubject() {
        return subject;
    }

    public String getHtmlBody() {
        return htmlBody;
    }

    public List<UUID> getUuidList() {
        return uuidList;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}