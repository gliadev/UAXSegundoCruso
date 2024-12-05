using UnityEngine;

public class PlayerMovement : MonoBehaviour
{
    public float speed = 5.0f;  // Velocidad de movimiento del jugador
    public float jumpForce = 5.0f;  // Fuerza de salto del jugador
    private bool isGrounded = true;  // Indica si el jugador est� en el suelo

    private Rigidbody rb;

    void Start()
    {
        // Obtener el componente Rigidbody para aplicar la f�sica (como el salto)
        rb = GetComponent<Rigidbody>();
    }

    void Update()
    {
        // Obt�n el input vertical (teclas de flecha arriba y abajo)
        float verticalInput = Input.GetAxis("Vertical");

        // Obt�n el input horizontal (teclas de flecha izquierda y derecha)
        float horizontalInput = Input.GetAxis("Horizontal");

        // Aplica movimiento hacia adelante, atr�s, izquierda y derecha
        Vector3 movement = new Vector3(horizontalInput, 0, verticalInput) * speed * Time.deltaTime;
        transform.Translate(movement);

        // Realizar el salto al presionar la barra espaciadora, si est� en el suelo
        if (Input.GetKeyDown(KeyCode.Space) && isGrounded)
        {
            rb.AddForce(Vector3.up * jumpForce, ForceMode.Impulse);
            isGrounded = false;
        }
    }

    // Detectar cuando el jugador toca el suelo para permitir saltar nuevamente
    private void OnCollisionEnter(Collision collision)
    {
        if (collision.gameObject.CompareTag("Ground"))
        {
            isGrounded = true;
        }
    }
}
