package com.ironijunior.energyconsumption.exception;

/**
 * Class that represents an exception for the cases where the
 * application could not find the entity for the identification.
 *
 * @author Ironi Junior Medina
 */
public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7923675795818037287L;
	
	private static final String MSG = "There is no data with the identifier %s.";

    /**
     * Constructor that receives a identifier
     *
     * @param id entity identifier
     */
    public EntityNotFoundException(String id) {
        super(String.format(MSG, id));
    }

}
