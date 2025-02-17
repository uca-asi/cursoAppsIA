package es.uca.cursoia.generators.text;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.cursoia.MainLayout;

@PageTitle("Generador de texto")
@Route(value = "generators/text", layout = MainLayout.class)
public class TextGeneratorView extends VerticalLayout {

    private final TextGeneratorService textGeneratorService;

    public TextGeneratorView(TextGeneratorService textGeneratorService) {
        this.textGeneratorService = textGeneratorService;

        // Build the form
        FormLayout formLayout = new FormLayout();
        formLayout.setWidth("60%");
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));

        TextArea input = new TextArea();
        input.setLabel("Prompt");
        input.setPlaceholder("Introduce el texto a procesar");
        input.setHeight("200px");
        formLayout.add(input);

        Button button = new Button("Generar");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        formLayout.add(button);

        TextArea output = new TextArea();
        output.setLabel("Resultado");
        output.setReadOnly(true);
        formLayout.add(output);

        // Add the button click listener
        button.addClickListener(e -> {
            String text = input.getValue();
            String result = textGeneratorService.generate(text);
            output.setValue(result);
        });

        // Set the layout
        this.add(formLayout);
        this.setJustifyContentMode(JustifyContentMode.CENTER); // Vertical alignment

        this.setSizeFull();

        this.setAlignSelf(Alignment.CENTER, formLayout);
    }


}