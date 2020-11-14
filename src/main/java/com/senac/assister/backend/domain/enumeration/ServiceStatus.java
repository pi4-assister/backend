package com.senac.assister.backend.domain.enumeration;

/**
 * All status responses
 * QUOTED: first status, shows the quotation of the service
 * ACCEPTED: if both clients accepted the quotation.
 */
public enum  ServiceStatus {
    CANCELED,
    INITIATED,
    IN_PROGRESS,
    FINISHED
}
