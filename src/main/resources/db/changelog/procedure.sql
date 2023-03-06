Drop PROCEDURE if exists sp_sumTotalInCart //

CREATE PROCEDURE sp_sumTotalInCart(
	In cartId int,
    out updated bool
)BEGIN
IF exists (SELECT * FROM cart c join cart_detail d on d.cart_id = c.id WHERE c.id = cartId) THEN
BEGIN
     DECLARE total DOUBLE DEFAULT 0.0;
		set total = (select sum(d.quantity * v.price) 
		from cart_detail d 
		join cart c on d.cart_id = c.id
		join product_variant v on v.id = d.product_variant_id
		where c.id = cartId);        
        update cart set price_sum = total where id = cartId; 
        set updated = true;
END;
ELSE
BEGIN
	set updated = false;
END;
END IF;
END //

