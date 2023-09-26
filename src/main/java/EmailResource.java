import io.quarkus.mailer.Mail;
import io.quarkus.mailer.reactive.ReactiveMailer;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/send-email")
public class EmailResource {
    @Inject
    ReactiveMailer reactiveMailer;

    @POST
    public Response sendEmail() {
        reactiveMailer.send(Mail.withText("EMAIL@DESTINO.COM", "A simple email from Quarkus", "This is my body").addCc("SEUEMAIL@GMAIL.COM")).subscribe().with(
                success -> System.out.println("Email sent!"),
                failure -> System.out.println("Failed to send email: " + failure.getMessage())
        );
        return Response.ok().build();
    }
}
