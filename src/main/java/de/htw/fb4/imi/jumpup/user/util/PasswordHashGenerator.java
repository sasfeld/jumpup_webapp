/**
 * JumpUp.Me Car Pooling Application
 *
 * Copyright (c) 2014 Sebastian Renner, Marco Seidler, Sascha Feldmann
 */
package de.htw.fb4.imi.jumpup.user.util;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import de.htw.fb4.imi.jumpup.settings.BeanNames;

/**
 * <p></p>
 *
 * @author <a href="mailto:me@saschafeldmann.de">Sascha Feldmann</a>
 * @since 07.11.2014
 *
 */
@Named(BeanNames.PASSWORD_HASH_GENERATOR)
@ApplicationScoped
public class PasswordHashGenerator extends AbstractHashGenerable
{

    @Override
    /*
     * (non-Javadoc)
     * @see de.htw.fb4.imi.jumpup.user.util.HashGenerable#generateHash(java.lang.String)
     */
    public byte[] generateHash(final String rawSecret)
    {
        return this.getSha256Digest().digest(rawSecret.getBytes());
    }
}
